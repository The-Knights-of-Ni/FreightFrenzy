package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Control;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

@Autonomous(name = "Auto Blue Secondary Cycle", group = "Auto Blue")
public class AutoBlueSecondaryCycle extends Auto {
    @Override
    public void runOpMode() throws InterruptedException {
        initAuto(AllianceColor.BLUE);
        PlacementLevel placementLevel = getHubLevel();
        waitForStart();
        Drive drive = robot.drive;

        robot.control.setLidPosition(Control.LidPosition.CLOSED);

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
        drive.turnByAngle(-30);
        drive.moveForward((22 + adjustment) * mmPerInch);

        // Release clamp
        robot.control.setLidPosition(Control.LidPosition.DEPLOYED);
        sleep(500);
        drive.moveBackward((22 + adjustment) * mmPerInch);

        robot.control.setLidPosition(Control.LidPosition.CLOSED);
        robot.control.setSlide(Control.SlideState.RETRACTED);

        // Move back to the warehouse
        drive.turnByAngle(-60);
        drive.moveRight(5 * mmPerInch);
        robot.control.setIntakeDirection(true, true);
        drive.moveBackward(31 * mmPerInch);
        robot.control.setLidPosition(Control.LidPosition.OPEN);

        for(int i = 0; i < 1; i++) {
            forwardCycle(i);
            backCycle(i);
        }

        // Ready devices for teleop
        robot.control.setIntakeDirection(false, false);
        robot.control.setLidPosition(Control.LidPosition.OPEN);
        sleep(3000);

        telemetry.addLine("Done");
        telemetry.update();
    }

    public void backCycle(int i) {
        robot.control.setLidPosition(Control.LidPosition.CLOSED);
        robot.control.setSlide(Control.SlideState.RETRACTED);
        robot.drive.moveBackward(24 * mmPerInch);

        robot.drive.turnByAngle(-60);
        robot.drive.moveRight(10 * mmPerInch);
        robot.control.setIntakeDirection(true, true);
        robot.drive.moveBackward((36 + i * 8)*mmPerInch);
        robot.control.setLidPosition(Control.LidPosition.OPEN);
    }
    public void forwardCycle(int i) {
        sleep(500);
        robot.control.setBucketState(Control.BucketState.RAISED);
        robot.drive.moveForward((36 + i * 8)*mmPerInch);
        robot.control.setLidPosition(Control.LidPosition.CLOSED);
        robot.control.setIntakeDirection(true, false);
        robot.control.setBucketState(Control.BucketState.LEVEL);
        robot.control.setSlide(Control.SlideState.TOP);

        robot.drive.moveLeft(4 * mmPerInch);
        robot.drive.turnByAngle(60);
        robot.control.setIntakeDirection(false, false);
        robot.drive.moveForward(24*mmPerInch);
        robot.control.setLidPosition(Control.LidPosition.DEPLOYED);
        sleep(250);
    }
}