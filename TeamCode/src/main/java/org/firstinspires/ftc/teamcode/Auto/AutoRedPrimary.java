package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

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
@Autonomous(name = "Auto Red Primary", group = "Auto Red")
public class AutoRedPrimary extends Auto {
    /**
     * Override of {@link Auto#runOpMode()}
     *
     * <p>Please do not swallow the InterruptedException, as it is used in cases where the op mode
     * needs to be terminated early.
     *
     * @throws InterruptedException
     * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
     */
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = null;
        try {
            robot = init(AllianceColor.RED);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert robot != null;
        int placementLevel;
        Drive drive = robot.drive;

        placementLevel = getHubLevel(robot.vision);
        waitForStart();

        robot.control.setBucketState(1);
        drive.moveForward(3 * mmPerInch);
        drive.turnRobotByTick(-80);
        drive.moveBackward(24 * mmPerInch);
//        robot.control.setBucketState(1); //TODO adjust this back to 90 once robot is heavier
        robot.control.startCarousel(false);
        sleep(3800);
        robot.control.stopCarousel();
        drive.moveForward(48 * mmPerInch);
        drive.turnRobotByTick(80); //TODO adjust this back to 90 once robot is heavier
        drive.moveForward(18 * mmPerInch);
        sleep(1000); // delivery point here
        drive.moveBackward(4 * mmPerInch);
        drive.turnRobotByTick(80); //TODO adjust this back to 90 once robot is heavier
        drive.moveLeft(20 * mmPerInch);
        robot.control.setIntakeDirection(true, false);
        drive.moveBackward(56 * mmPerInch);
        robot.control.setIntakeDirection(false, false);
        telemetry.addLine("Done");
        telemetry.update();
    }
}
