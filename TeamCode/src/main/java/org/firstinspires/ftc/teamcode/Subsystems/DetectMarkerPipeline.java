package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * This pipeline detects where the custom marker is.
 *
 * <p>It does this by splitting the camera input into 3 parts, the Left, Middle, and Right. It
 * checks each part for a custom marker (which is set to be green in the code), or some blue or red
 * tape, dependant on the alliance color. The marker is assumed to be yellow.</p>
 *
 * @see OpenCvPipeline
 * @see Vision
 */
public class DetectMarkerPipeline extends OpenCvPipeline {
    private final AllianceColor allianceColor;
    private final Rect LEFT_RECT = new Rect(new Point(0, 0), new Point(640, 1080));
    private final Rect MIDDLE_RECT = new Rect(new Point(640, 0), new Point(1280, 1080));
    private final Rect RIGHT_RECT = new Rect(new Point(1280, 0), new Point(1920, 1080));
    private final double PERCENT_COLOR_THRESHOLD = 0.1;
    private final int CAMERA_WIDTH;
    Telemetry telemetry;
    private MarkerLocation markerLocation = MarkerLocation.NOT_FOUND;

    public enum MarkerLocation {
        LEFT, MIDDLE, RIGHT, NOT_FOUND
    }

    /**
     * Class instantiation
     *
     * @param telemetry used for {@link Telemetry}
     * @see Robot
     * @see Telemetry
     * @see AllianceColor
     */
    public DetectMarkerPipeline(Telemetry telemetry, AllianceColor allianceColor, int width) {
        this.telemetry = telemetry;
        this.allianceColor = allianceColor;
        this.CAMERA_WIDTH = width;
    }

    /**
     * This method detects where the marker is.
     *
     * <p>It does this by splitting the camera input into left, right, and middle rectangles, these
     * rectangles need to be calibrated. Combined, they do not have to encompass the whole camera
     * input, they probably will only check a small part of it. We then assume the alliance color is
     * either (255, 0, 0) or (0, 0, 255), we get the info when the object is instantiated ({@link
     * #allianceColor}), and that the marker color is (0, 255, 0), which is a bright green ({@link
     * Scalar}'s are used for colors). We compare the marker color with the alliance color on each of
     * the rectangles, if the marker color is on none or multiple of them, it is marked as {@link
     * MarkerLocation#NOT_FOUND}, if otherwise, the respective Location it is in is returned via a
     * {@link MarkerLocation} variable called {@link #markerLocation}
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
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        if(mat.empty()) {
            markerLocation = MarkerLocation.NOT_FOUND;
            return input;
        }

        Scalar lowHSV = new Scalar(20, 100, 100);
        Scalar highHSV = new Scalar(30, 255, 255);
        Mat thresh = new Mat();

        Core.inRange(mat, lowHSV, highHSV, thresh);

        Mat edges = new Mat();
        Imgproc.Canny(thresh, edges, 100, 300);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        MatOfPoint2f[] contoursPoly = new MatOfPoint2f[contours.size()];
        Rect[] boundRect = new Rect[contours.size()];

        for(int i = 0; i < contours.size(); i++) {
            contoursPoly[i] = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
            boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
        }

        double left_x = 0.4 * CAMERA_WIDTH;
        double right_x = 0.6 * CAMERA_WIDTH;

        boolean left = false;
        boolean middle = false;
        boolean right = false;

        for(int i = 0; i != boundRect.length; i++) {
            if(boundRect[i].x + boundRect[i].width < left_x)
                left = true;
            if(left_x <= boundRect[i].x && boundRect[i].x + boundRect[i].width <= right_x)
                middle = true;
            if(right_x < boundRect[i].x)
                right = true;
        }
        if(left) markerLocation = MarkerLocation.LEFT;
        if(middle) markerLocation = MarkerLocation.MIDDLE;
        if(right) markerLocation = MarkerLocation.RIGHT;

        return thresh;
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
