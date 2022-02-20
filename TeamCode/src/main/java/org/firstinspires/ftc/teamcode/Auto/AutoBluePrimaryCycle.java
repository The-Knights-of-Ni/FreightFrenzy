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
        drive.moveForward(1 * mmPerInch);
        robot.control.startCarousel(true);
        drive.moveRight(27 * mmPerInch);

        // Deliver Duck
        sleep(2500);
        robot.control.stopCarousel();

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
        drive.turnByAngle(70);
        drive.moveForward((48 + adjustment) * mmPerInch);

        // Release clamp
        robot.control.setLidPosition(LidPosition.DEPLOYED);
        sleep(500);
        drive.moveBackward((4 + adjustment) * mmPerInch);

        // Move back to warehouse
        robot.control.setLidPosition(LidPosition.CLOSED);
        robot.control.setSlide(SlideState.RETRACTED);
        drive.turnByAngle(-160);
        drive.moveRight(36 * mmPerInch);

        // Begin cycles
        robot.control.setIntakeDirection(true, true);
        robot.drive.moveBackward(156 * mmPerInch);
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

        robot.drive.turnByAngle(-60);
        robot.drive.moveRight(10 * mmPerInch);
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

        robot.drive.moveLeft(4 * mmPerInch);
        robot.drive.turnByAngle(60);
        robot.control.setIntakeDirection(false, false);
        robot.drive.moveForward(24*mmPerInch);
        robot.control.setLidPosition(LidPosition.DEPLOYED);
        sleep(250);
    }
}
