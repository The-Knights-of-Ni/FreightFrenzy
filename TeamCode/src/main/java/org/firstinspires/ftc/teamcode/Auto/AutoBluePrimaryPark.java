package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Control.BucketState;
import org.firstinspires.ftc.teamcode.Subsystems.Control.LidPosition;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;
import org.firstinspires.ftc.teamcode.Subsystems.Control.SlideState;
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
@Autonomous(name = "Auto Blue Primary Park", group = "Auto Blue")
public class AutoBluePrimaryPark extends Auto {
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
        drive.moveBackward(27 * mmPerInch);

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
        drive.turnByAngle(20);
        drive.moveBackward(36 * mmPerInch);

        // Ready devices for teleop
        robot.control.setIntakeDirection(false, false);
        robot.control.setLidPosition(LidPosition.OPEN);
        sleep(3000);

        telemetry.addLine("Done");
        telemetry.update();
    }
}
