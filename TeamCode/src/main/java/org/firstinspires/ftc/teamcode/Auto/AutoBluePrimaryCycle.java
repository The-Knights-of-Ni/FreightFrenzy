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
@Autonomous(name = "Auto Blue Primary Cycle", group = "Auto Blue")
public class AutoBluePrimaryCycle extends Auto {
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
        initAuto(AllianceColor.BLUE);
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
        drive.moveForward(2 * mmPerInch);
        robot.control.startCarousel(true);
        drive.moveRight(22 * mmPerInch);

        // Deliver Duck
        sleep(2500);
        robot.control.stopCarousel();

        // Move to hub (and start ScoreThread)
        new ScoreThread(robot, placementLevel).start();
        drive.moveLeft((48) * mmPerInch);
        double adjustment = 0;
        switch(placementLevel) {
            case BOTTOM:
                break;
            case MIDDLE:
            case TOP:
                adjustment = 4;
                break;
        }
        drive.moveForward((15 + adjustment) * mmPerInch);

        // Release clamp
        robot.control.setLidPosition(LidPosition.DEPLOYED);
        sleep(500);

        // Move back to warehouse
        drive.moveBackward(4 * mmPerInch);
        drive.turnByAngle(-90);
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setSlide(SlideState.RETRACTED);
        drive.moveRight((25 - adjustment) * mmPerInch);
        robot.control.setIntakeDirection(true, true);
        robot.drive.moveBackward(55 * mmPerInch);
        robot.control.setLidPosition(LidPosition.OPEN);

        sleep(500);
        forwardCycle();
        backCycle();

        // Ready devices for teleop
        robot.control.setIntakeDirection(false, false);
        robot.control.setBucketState(BucketState.LEVEL);
        robot.control.setLidPosition(LidPosition.OPEN);
        sleep(3000);

        telemetry.addLine("Done");
        telemetry.update();
    }

    public void backCycle() {
        robot.drive.moveBackward(6 * mmPerInch);
        robot.drive.turnByAngle(-90);
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.drive.moveRight(21 * mmPerInch);
        robot.control.setSlide(SlideState.RETRACTED);
        robot.control.setIntakeDirection(true, true);
        robot.drive.moveBackward((55) * mmPerInch);
        robot.control.setLidPosition(LidPosition.OPEN);
    }
    public void forwardCycle() {
        robot.control.setBucketState(BucketState.RAISED);
        robot.drive.moveForward((55)*mmPerInch);
        robot.drive.moveLeft(4*mmPerInch);
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setSlide(SlideState.TOP);
        robot.control.setIntakeDirection(true, false);
        robot.control.setBucketState(BucketState.LEVEL);
        robot.drive.turnByAngle(90);
        robot.control.setIntakeDirection(false, false);
        robot.drive.moveForward(20*mmPerInch);
        robot.control.setLidPosition(LidPosition.DEPLOYED);
        sleep(500);
    }
}
