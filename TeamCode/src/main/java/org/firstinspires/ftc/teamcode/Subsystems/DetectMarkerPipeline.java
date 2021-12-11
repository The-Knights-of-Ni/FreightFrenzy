package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

/**
 * This pipeline detects where the marker is.
 *
 * <p>It does this by splitting the camera input into 3 parts, the Left, Middle, and Right. It
 * checks each part for a custom marker (which is set to be green in the code), or some blue or red
 * tape, dependant on the alliance color.</p>
 *
 * @see OpenCvPipeline
 * @see Vision
 */
public class DetectMarkerPipeline extends OpenCvPipeline {
    public enum MarkerLocation {
        LEFT,
        MIDDLE,
        RIGHT,
        NOT_FOUND
    }

    private final AllianceColor allianceColor;
    private final Rect LEFT_RECT = new Rect(
            new Point(0, 0),
            new Point(640, 1080));
    private final Rect MIDDLE_RECT = new Rect(
            new Point(426, 0),
            new Point(1280, 1080));
    private final Rect RIGHT_RECT = new Rect(
            new Point(852, 0),
            new Point(1920, 1080));
    private final double PERCENT_COLOR_THRESHOLD = 0.1;
    Telemetry telemetry;
    Mat mask = new Mat();
    private MarkerLocation markerLocation = MarkerLocation.NOT_FOUND;

    /**
     * Class instantiation
     *
     * @param telemetry used for {@link Telemetry}
     * @see Robot
     * @see Telemetry
     * @see AllianceColor
     */
    public DetectMarkerPipeline(Telemetry telemetry, AllianceColor allianceColor) {
        this.telemetry = telemetry;
        this.allianceColor = allianceColor;
    }

    /**
     * This method detects where the marker is.
     *
     * <p>It does this by splitting the camera input into left, right, and middle rectangles, these
     * rectangles need to be calibrated. Combined, they do not have to encompass the whole camera
     * input, they probably will only check a small part of it. We then assume the alliance color is
     * either (255, 0, 0) or (0, 0, 255), we get the info when the object is instantiated
     * ({@link #allianceColor}), and that the marker color is (0, 255, 0), which is a bright green
     * ({@link Scalar}'s are used for colors). We compare the marker color with the alliance color
     * on each of the rectangles, if the marker color is on none or multiple of them, it is marked
     * as {@link MarkerLocation#NOT_FOUND}, if otherwise, the respective Location it is in is
     * returned via a {@link MarkerLocation} variable called {@link #markerLocation}</p>
     *
     * @param input A Mask (the class is called {@link Mat})
     * @return The marker location
     * @see #allianceColor
     * @see Mat
     * @see Scalar
     * @see MarkerLocation
     */
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mask, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(25, 25, 35);
        Scalar highHSV = new Scalar(40, 255, 255);

        Core.inRange(mask, lowHSV, highHSV, mask);

        Mat left = mask.submat(LEFT_RECT);
        Mat middle = mask.submat(MIDDLE_RECT);
        Mat right = mask.submat(RIGHT_RECT);

        double leftValue = Core.sumElems(left).val[0] / LEFT_RECT.area() / 255;
        double middleValue = Core.sumElems(middle).val[0] / MIDDLE_RECT.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_RECT.area() / 255;

        left.release();
        middle.release();
        right.release();

//        telemetry.addData("Left raw value", ((Integer) ((int) Core.sumElems(left).val[0])).toString());
//        telemetry.addData("Middle raw value", ((Integer) ((int) Core.sumElems(middle).val[0])).toString());
//        telemetry.addData("Right raw value", ((Integer) ((int) Core.sumElems(right).val[0])).toString());
//
//        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
//        telemetry.addData("Middle percentage", Math.round(leftValue * 100) + "%");
//        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");
//        telemetry.update();

        double greatestValue = leftValue;

        markerLocation = MarkerLocation.LEFT;
        if (middleValue > greatestValue) {
            markerLocation = MarkerLocation.MIDDLE;
            greatestValue = middleValue;
        }
        if (rightValue > greatestValue) {
            markerLocation = MarkerLocation.RIGHT;
        }

        String result = "NOT_FOUND";
        switch (markerLocation) {
            case LEFT:
                result = "LEFT";
                break;
            case MIDDLE:
                result = "MIDDLE";
                break;
            case RIGHT:
                result = "RIGHT";
                break;
        }
        telemetry.addData("Marker Location", result);

        telemetry.update();

        Imgproc.cvtColor(mask, mask, Imgproc.COLOR_GRAY2RGB); // TODO: Change COLOR_GRAY2RGB to something more useful.

        Scalar colorNormal;

        if (this.allianceColor == AllianceColor.RED) {
            colorNormal = new Scalar(255, 0, 0); // Pure Red
        } else if (this.allianceColor == AllianceColor.BLUE) {
            colorNormal = new Scalar(0, 0, 255); // Pure Blue
        } else {
            colorNormal = new Scalar(255, 0, 255); // Pure Blue
        }

        Scalar colorMarker = new Scalar(0, 255, 0); // Pure Green

        Imgproc.rectangle(mask, LEFT_RECT, markerLocation == MarkerLocation.LEFT ? colorMarker : colorNormal);
        Imgproc.rectangle(mask, MIDDLE_RECT, markerLocation == MarkerLocation.MIDDLE ? colorMarker : colorNormal);
        Imgproc.rectangle(mask, RIGHT_RECT, markerLocation == MarkerLocation.RIGHT ? colorMarker : colorNormal);

        return mask;
    }

    /**
     * Gets the Marker Location, might be not found because of the Search Status.
     *
     * @return Where the marker is.
     * @see MarkerLocation
     */
    public MarkerLocation getMarkerLocation() {
        return markerLocation;
    }
}
