package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

@Autonomous(name = "Auto Red Secondary", group = "Auto Red")
public class AutoRedSecondary extends Auto {
    @Override
    public void runOpMode() throws InterruptedException {
        try {
            initAuto(AllianceColor.RED);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert robot != null;
        waitForStart();
        int placementLevel;
        Drive drive = robot.drive;

        placementLevel = getHubLevel();
        telemetry.addData("Location", placementLevel);
        telemetry.update();

        // Move in front of the big pole thingy
        drive.moveForward(7 * mmPerInch);
        drive.turnRobotByTick(45);
        drive.moveForward(18 * mmPerInch);
        sleep(1000);

        // Move back to the warehouse
        drive.turnRobotByTick(45);
        drive.moveBackward(74 * mmPerInch);

    }
}
