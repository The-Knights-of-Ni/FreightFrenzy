package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

/**
 * Auto creates a robots and runs it in auto mode. This auto class is for when we are on the blue
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
@Autonomous(name = "Auto Blue Primary", group = "Auto Blue")
public class AutoBluePrimary extends Auto {
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
        try {
            initAuto(AllianceColor.BLUE);
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

        // Move to carousel
        robot.control.setBucketState(1);
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
