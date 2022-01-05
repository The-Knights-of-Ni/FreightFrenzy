package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Subsystems.Control;
import org.firstinspires.ftc.teamcode.Subsystems.Control.BucketState;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

/**
 * Auto Blue Primary creates a robots and runs it in auto mode. This auto class is for when we are on the blue and are
 * the primary auto
 * alliance.
 *
 * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
 */
@Autonomous(name = "Auto Blue Primary", group = "Auto Blue")
public class AutoBluePrimary extends Auto {
    // Tasks:
    // Deliver duck from carousel (10)
    // Deliver freight to hub (6)
    // - deliver freight to corresponding level of custom element (20)
    // Park in warehouse (10)
    /**
     * Override of runOpMode()
     *
     * <p>Please do not swallow the InterruptedException, as it is used in cases where the op mode
     * needs to be terminated early.
     *
     * @throws InterruptedException If the robot is terminated this is thrown.
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
        robot.control.setLidPosition(Control.LidPosition.CLOSED);
        drive.moveForward(3 * mmPerInch);
        drive.moveRight(20 * mmPerInch);

        // Deliver Duck
        robot.control.startCarousel(true);
        sleep(3800);
        robot.control.stopCarousel();

        // Move to hub (and start ScoreThread)
//        ScoreThread place = new ScoreThread(robot, placementLevel);
//        place.run();
//        drive.moveLeft(48 * mmPerInch);
//        drive.moveForward(9 * mmPerInch);
//
//        // Release clamp
//        sleep(1000); // delivery point here
//
//        // Move to warehouse
//        drive.moveBackward(4 * mmPerInch);
//        drive.turnRobotByTick(-80);
//        drive.moveRight(20 * mmPerInch);
//        robot.control.setIntakeDirection(true, false);
//        drive.moveBackward(56 * mmPerInch);
//        robot.control.setIntakeDirection(false, false);
    }
}
