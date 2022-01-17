package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Auto.Auto;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

@Autonomous(name = "Drive Configuration Test", group = "Concept")
public class DriveConfigTest extends Auto {
    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addLine("done!");
        telemetry.update();

        waitForStart();
        // Just drive forward 48 inches
        robot.control.setBucketState(Control.BucketState.LEVEL);
        robot.drive.moveForward(48 * mmPerInch);
        sleep(3000);
        robot.drive.moveForward(12 * mmPerInch);
        sleep(3000);
        robot.drive.turnRobotByTick(90); // Use a protractor if available
    }
}