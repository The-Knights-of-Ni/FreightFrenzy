package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.util.AllianceColor;

import java.io.IOException;

@Autonomous(name = "Auto Blue Secondary", group = "Auto Blue")
public class AutoBlueSecondary extends Auto {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = null;
        try {
            robot = init(AllianceColor.RED);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert robot != null;
        waitForStart();
        int placementLevel;
        Drive drive = robot.drive;

        placementLevel = getHubLevel(robot.vision);
        telemetry.addData("Location", placementLevel);
        telemetry.update();

        // Move in front of the hub
        drive.moveForward(7 * mmPerInch);
        drive.turnRobotByTick(-45);
        drive.moveForward(18 * mmPerInch);
        sleep(1000);

        // Move back to the warehouse
        drive.turnRobotByTick(-45);
        drive.moveForward(74 * mmPerInch);
    }
}
