package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Control;
import org.firstinspires.ftc.teamcode.Subsystems.Control.LidPosition;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;
import org.firstinspires.ftc.teamcode.Subsystems.Control.SlideState;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

@Autonomous(name = "Auto Red Secondary Cycle", group = "Auto Red")
public class AutoRedSecondaryCycle extends Auto {
    @Override
    public void runOpMode() throws InterruptedException {
        initAuto(AllianceColor.RED);
        PlacementLevel placementLevel = getHubLevel();
        waitForStart();
        Drive drive = robot.drive;

        robot.control.setLidPosition(LidPosition.CLOSED);

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
        drive.moveForward(2 * mmPerInch);
        drive.turnByAngle(32.5);
        drive.moveForward((21 + adjustment) * mmPerInch);

        // Release clamp
        robot.control.setLidPosition(LidPosition.DEPLOYED);
        sleep(500);
        drive.moveBackward((21 + adjustment) * mmPerInch);

        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setSlide(SlideState.RETRACTED);

        // Move back to the warehouse
        drive.turnByAngle(57.5);
        drive.moveLeft(5 * mmPerInch);

        robot.control.setIntakeDirection(true, true);
        robot.control.setLidPosition(LidPosition.OPEN);
        drive.moveBackward(32 * mmPerInch);

        for(int i = 0; i < 1; i++) {
            forwardCycle(i);
            backCycle(i);
        }

        // Ready devices for teleop
        robot.control.setIntakeDirection(false, false);
        robot.control.setLidPosition(LidPosition.OPEN);
        sleep(1000);

        telemetry.addLine("Done");
        telemetry.update();
    }

    public void backCycle(int i) {
        robot.drive.moveBackward(4 * mmPerInch);
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setSlide(SlideState.RETRACTED);
        robot.drive.moveBackward(20 * mmPerInch);

        robot.drive.turnByAngle(60);
        robot.drive.moveLeft(10 * mmPerInch);
        robot.control.setIntakeDirection(true, true);
        robot.drive.moveBackward((32 + i * 6)*mmPerInch);
        robot.control.setLidPosition(LidPosition.OPEN);
    }
    public void forwardCycle(int i) {
        sleep(250);
        robot.control.setBucketState(Control.BucketState.RAISED);
        robot.drive.moveForward((32 + i * 6)*mmPerInch);
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setIntakeDirection(true, false);
        robot.control.setBucketState(Control.BucketState.LEVEL);
        robot.control.setSlide(SlideState.TOP);

        robot.drive.moveRight(4 * mmPerInch);
        robot.drive.turnByAngle(-60);
        robot.control.setIntakeDirection(false, false);
        robot.drive.moveForward(24*mmPerInch);
        robot.control.setLidPosition(LidPosition.DEPLOYED);
        sleep(500);
    }
}