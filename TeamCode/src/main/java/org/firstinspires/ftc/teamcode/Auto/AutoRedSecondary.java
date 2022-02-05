package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Control;
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
                break;
            case MIDDLE:
            case TOP:
                adjustment = 4;
                break;
        }

        // Move in front of the big pole thingy
        drive.moveForward(2 * mmPerInch);
        drive.turnByAngle(30);
        drive.moveForward((20 + adjustment) * mmPerInch);

        // Release clamp
        robot.control.setLidPosition(Control.LidPosition.DEPLOYED);
        sleep(500);
        drive.moveBackward((20 + adjustment) * mmPerInch);

        // Move back to the warehouse
        drive.turnByAngle(60);
        drive.moveLeft(5 * mmPerInch);
        drive.moveBackward(27 * mmPerInch);
        drive.moveRight(2 * mmPerInch);
        drive.turnByAngle(-90);
        drive.moveForward(27 * mmPerInch);
        drive.moveRight(27 * mmPerInch);
    }
}