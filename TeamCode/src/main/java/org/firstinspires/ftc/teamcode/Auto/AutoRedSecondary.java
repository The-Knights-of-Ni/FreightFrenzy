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

        // Move to hub (and start ScoreThread)
        new ScoreThread(robot, placementLevel).start();
        double adjustment = 0;
        switch(placementLevel) {
            case BOTTOM:
                adjustment = 1.5;
                break;
            case MIDDLE:
            case TOP:
                adjustment = 5;
                break;
        }

        // Move in front of the big pole thingy
        drive.moveForward(2 * mmPerInch);
        drive.turnByAngle(30);
        drive.moveForward((19 + adjustment) * mmPerInch);
//        sleep(1000);
//
//        // Move back to the warehouse
//        drive.turnByAngle(50);
//        drive.moveBackward(74 * mmPerInch);

    }
}