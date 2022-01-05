package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Subsystems.Control;
import org.firstinspires.ftc.teamcode.Subsystems.Control.BucketState;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;
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
@Autonomous(name = "Auto Red Primary", group = "Auto Red")
public class AutoRedPrimary extends Auto {
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
        robot.control.setLidPosition(Control.LidPosition.CLOSED);
        drive.moveForward(3 * mmPerInch);
        drive.turnRobotByTick(-80); //TODO adjust this back to 90 once robot is heavier
        drive.moveBackward(24.8 * mmPerInch);

        // Deliver Duck
        robot.control.startCarousel(false);
        sleep(3500);
        robot.control.stopCarousel();

        // Move to hub (and start ScoreThread)
        new ScoreThread(robot, placementLevel).start();
//        robot.control.setSlide(placementLevel); // Can be used instead of multithreading

        drive.moveForward(48 * mmPerInch);
        drive.turnRobotByTick(85); //TODO adjust this back to 90 once robot is heavier

        double adjustment = 0;
        switch(placementLevel) {
            case BOTTOM:
                adjustment = 2;
                break;
            case MIDDLE:
            case TOP:
                adjustment = 4;
                break;
        }
        drive.moveForward((15 + adjustment) * mmPerInch); //TODO adjust this constant


        // Release clamp
        robot.control.setLidPosition(Control.LidPosition.DEPLOYED);
        sleep(1000);

        // Move back to warehouse
        drive.moveBackward(4 * mmPerInch);
        drive.turnRobotByTick(80); //TODO adjust this back to 90 once robot is heavier
        robot.control.setLidPosition(Control.LidPosition.CLOSED);
        robot.control.setSlide(Control.SlideState.RETRACTED);
        drive.moveLeft((23.5 - adjustment) * mmPerInch);

        robot.control.setIntakeDirection(true, false);
        drive.moveBackward(56 * mmPerInch);
        robot.control.setIntakeDirection(false, false);

        telemetry.addLine("Done");
        telemetry.update();
    }
}
