package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.DetectMarkerPipeline;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

@Autonomous(name = "PID Test", group = "Concept")
public class PIDTest extends LinearOpMode {
    private static final float mmPerInch        = 25.4f;

    private Robot robot;
    public ElapsedTime timer;

    private void initOpMode() throws IOException {
        telemetry.addData("Init Robot", "");
        telemetry.update();
        timer = new ElapsedTime();

        this.robot = new Robot(this, timer, AllianceColor.BLUE);

        telemetry.addData("Wait for start", "");
        telemetry.update();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            initOpMode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DetectMarkerPipeline.MarkerLocation location;

        waitForStart();

//        while (opModeIsActive()) {
//            location = vision.detectMarkerRun();
//            switch(location) {
//                case LEFT:
//                    telemetry.addData("Location", "LEFT");
//                    break;
//                case MIDDLE:
//                    telemetry.addData("Location", "MIDDLE");
//                    break;
//                case RIGHT:
//                    telemetry.addData("Location", "RIGHT");
//                    break;
//                case NOT_FOUND:
//                    telemetry.addData("Location", "NOT_FOUND");
//                    break;
//            }
//            telemetry.update();
//        }
        robot.drive.moveForward(12*mmPerInch);
    }
}