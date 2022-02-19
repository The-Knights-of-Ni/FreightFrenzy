package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Subsystems.Control.*;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

/**
 * Auto creates a robots and runs it in auto mode. This auto class is for when we are on the red
 * alliance.
 *
 * <p>Auto currently just initializes the Robot as Auto.runOpMode() is empty.
 *
 * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
 */

// Tasks:
// Deliver duck from carousel (10)
// Deliver freight to hub (6)
// - deliver freight to corresponding level of custom element (20)
// Park in warehouse (10)
@Autonomous(name = "Auto Red Primary 2", group = "Auto Red")
public class AutoRedPrimary2 extends Auto {
    /**
     * Override of {@link Auto#runOpMode()}
     *
     * <p>Please do not swallow the InterruptedException, as it is used in cases where the op mode
     * needs to be terminated early.
     *
     * @throws InterruptedException Thrown when the op mode needs to be terminated early.
     * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
     */
    @Override
    public void runOpMode() throws InterruptedException {
        initAuto(AllianceColor.RED);
        PlacementLevel placementLevel = getHubLevel();
        waitForStart();
        Drive drive = robot.drive;

        if (placementLevel != PlacementLevel.NOT_FOUND) {
            telemetry.addData("Level", placementLevel);
            telemetry.update();
        }

        // Move to carousel
        robot.control.setBucketState(BucketState.LEVEL);
        robot.control.setLidPosition(LidPosition.CLOSED);
        drive.moveForward(1 * mmPerInch);
        drive.turnByAngle(-90);
        robot.control.startCarousel(false);
        drive.moveBackward(24 * mmPerInch);

        // Deliver Duck
        sleep(2500);
        robot.control.stopCarousel();

        // Move to hub (and start ScoreThread)
        new ScoreThread(robot, placementLevel).start();
		double adjustment = 0;
        drive.turnByAngle(17);
        switch(placementLevel) {
            case BOTTOM:
                break;
            case MIDDLE:
            case TOP:
                adjustment = 4;
                break;
        }
        drive.moveForward((48) * mmPerInch);
        drive.turnByAngle(70);
        drive.moveForward(adjustment * mmPerInch);

        // Release clamp
        robot.control.setLidPosition(LidPosition.DEPLOYED);
        sleep(500);
        drive.moveBackward(adjustment * mmPerInch);

        // Move back to warehouse
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setSlide(SlideState.RETRACTED);
        drive.turnByAngle(60);
        drive.moveBackward(30 * mmPerInch);
        drive.turnByAngle(30);
        drive.moveLeft(10 * mmPerInch);

        // Begin cycles
        robot.control.setIntakeDirection(true, true);
        robot.drive.moveBackward(36 * mmPerInch);
        robot.control.setLidPosition(LidPosition.OPEN);

        for(int i = 0; i < 1; i++) {
            forwardCycle(i);
            backCycle(i);
        }

        // Ready devices for teleop
        robot.control.setIntakeDirection(false, false);
        robot.control.setLidPosition(LidPosition.OPEN);
        sleep(3000);

        telemetry.addLine("Done");
        telemetry.update();
    }

    public void backCycle(int i) {
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setSlide(SlideState.RETRACTED);
        robot.drive.moveBackward(24 * mmPerInch);

        robot.drive.turnByAngle(60);
        robot.drive.moveLeft(10 * mmPerInch);
        robot.control.setIntakeDirection(true, true);
        robot.drive.moveBackward((36 + i * 8)*mmPerInch);
        robot.control.setLidPosition(LidPosition.OPEN);

    }
    public void forwardCycle(int i) {
        sleep(500);
        robot.control.setBucketState(BucketState.RAISED);
        robot.drive.moveForward((36 + i * 8)*mmPerInch);
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setIntakeDirection(true, false);
        robot.control.setBucketState(BucketState.LEVEL);
        robot.control.setSlide(SlideState.TOP);

        robot.drive.moveRight(4 * mmPerInch);
        robot.drive.turnByAngle(-60);
        robot.control.setIntakeDirection(false, false);
        robot.drive.moveForward(24*mmPerInch);
        robot.control.setLidPosition(LidPosition.DEPLOYED);
        sleep(250);
    }
}
