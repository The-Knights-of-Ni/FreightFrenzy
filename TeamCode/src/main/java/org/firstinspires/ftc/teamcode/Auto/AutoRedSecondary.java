package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

@Autonomous(name = "Auto Red Secondary", group = "Auto Red")
public class AutoRedSecondary extends Auto {
    @Override
    public void runOpMode() throws InterruptedException {
        initAuto(AllianceColor.RED);
        PlacementLevel placementLevel = getHubLevel();
        waitForStart();
        Drive drive = robot.drive;

        if(placementLevel != PlacementLevel.NOT_FOUND) {
            telemetry.addData("Level", placementLevel);
            telemetry.update();
        }

        // Move in front of the big pole thingy
        drive.moveForward(7 * mmPerInch);
        drive.turnByAngle(45);
        drive.moveForward(18 * mmPerInch);
        sleep(1000);

        // Move back to the warehouse
        drive.turnByAngle(45);
        drive.moveBackward(74 * mmPerInch);

    }
}
