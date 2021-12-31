package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.Subsystems.DetectMarkerPipeline;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

/**
 * This shows what the camera is seeing
 */
@Autonomous(name = "CameraPOV", group = "Concept")
public class CameraPOV extends LinearOpMode {
    public static final int CAMERA_WIDTH = 1920; // width of wanted camera resolution
    public static final int CAMERA_HEIGHT = 1080; // height of wanted camera resolution
    public static final int HORIZON = 100; // horizon value to tune
    public static final String WEBCAM_NAME =
            "Webcam 1"; // insert webcam name from configuration if using webcam
    public static final String VUFORIA_KEY =
            "ATDGULf/////AAABmRRGSyLSbUY4lPoqBYjklpYqC4y9J7bCk42kjgYS5KtgpKL8FbpEDQTovzZG8thxB01dClvthxkSuSyCkaZi+JiD5Pu0cMVre3gDwRvwRXA7V9kpoYyMIPMVX/yBTGaW8McUaK9UeQUaFSepsTcKjX/itMtcy7nl1k84JChE4i8whbinHWDpaNwb5qcJsXlQwJhE8JE7t8NMxMm31AgzqjVf/7HwprTRfrxjTjVx5v2rp+wgLeeLTE/xk1JnL3fZMG6yyxPHgokWlIYEBZ5gBX+WJfgA+TDsdSPY/MnBp5Z7QxQsO9WJA59o/UzyEo/9BkbvYJZfknZqeoZWrJoN9jk9sivFh0wIPsH+JjZNFsPw"; // TODO: Get new VUFORIA KEY
    // Since ImageTarget trackable use mm to specify their dimensions, we must use mm for all the
    // physical dimension.
    // Define constants
    private static final double mmPerInch = 25.4;
    // Constants for perimeter targets
    private static final double halfField = 72 * mmPerInch;
    private static final double quadField = 36 * mmPerInch;
    // Define where camera is in relation to center of robot in inches
    final double CAMERA_FORWARD_DISPLACEMENT = 6.0f * mmPerInch; // TODO: CALIBRATE WHEN ROBOT IS BUILT
    final double CAMERA_VERTICAL_DISPLACEMENT = 6.5f * mmPerInch;
    final double CAMERA_LEFT_DISPLACEMENT = -0.75f * mmPerInch;
    WebcamName webcamName = null;
    OpenGLMatrix robotFromCamera = null;

    // Class Members
    private OpenGLMatrix lastLocation;
    private VuforiaLocalizer vuforia;

    private boolean targetVisible;
    private VectorF targetTranslation;
    private Orientation targetRotation;

    private DetectMarkerPipeline pipeline;
    private OpenCvCamera camera;

    private void initCamera() {
        int cameraMonitorViewId =
                hardwareMap
                        .appContext
                        .getResources()
                        .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        camera =
                OpenCvCameraFactory.getInstance()
                        .createWebcam(hardwareMap.get(WebcamName.class, WEBCAM_NAME), cameraMonitorViewId);
        camera.openCameraDeviceAsync(
                new OpenCvCamera.AsyncCameraOpenListener() {
                    @Override
                    public void onOpened() {
                        telemetry.addLine("Streaming");
                        camera.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
                    }

                    @Override
                    public void onError(int errorCode) {
                        telemetry.addLine("Error Streaming, aborting");
                        telemetry.update();
                    }
                });
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initCamera();
        waitForStart();
    }
}
