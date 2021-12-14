package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Subsystems.DetectMarkerPipeline;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

@Autonomous(name = "Vision Test", group = "Concept")
public class VisionTest extends LinearOpMode {
  @Override
  public void runOpMode() throws InterruptedException {
    ElapsedTime timer = new ElapsedTime();
    Vision vision = new Vision(telemetry, hardwareMap, timer, AllianceColor.BLUE);

    DetectMarkerPipeline.MarkerLocation location;

    waitForStart();

    while (opModeIsActive()) {
      location = vision.detectMarkerRun();
    }

    //        DetectMarkerPipeline.MarkerLocation location = vision.detectMarkerRun();

    //        telemetry.addData("Location", "NOT_FOUND");
    //        telemetry.update();

    //        switch(location) {
    //            case LEFT:
    //                telemetry.addData("Location", "LEFT");
    //                break;
    //            case MIDDLE:
    //                telemetry.addData("Location", "MIDDLE");
    //                break;
    //            case RIGHT:
    //                telemetry.addData("Location", "RIGHT");
    //                break;
    //            case NOT_FOUND:
    //                telemetry.addData("Location", "NOT_FOUND");
    //                break;
    //            case SEARCHING:
    //                telemetry.addData("Location", "SEARCHING");
    //                break;
    //        }
    //        telemetry.update();
  }
}
