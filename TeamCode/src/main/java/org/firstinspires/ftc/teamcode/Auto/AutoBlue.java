package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;


/**
 * Auto creates a robots and runs it in auto mode. This auto class is for when we are
 * on the blue alliance.
 *
 * <p>Auto currently just initializes the Robot as Auto.runOpMode() is empty.</p>
 *
 * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
 */

//Tasks:
// Deliver duck from carousel (10)
//Deliver freight to hub (6)
// - deliver freight to corresponding level of custom element (20)
//Park in warehouse (10)
@Autonomous(name = "Auto Blue", group = "Auto")
public class AutoBlue extends Auto {
    /**
     * Override of runOpMode()
     *
     * <p>Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.</p>
     *
     * @throws InterruptedException If the robot is terminated this is thrown.
     * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
     */
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = init(AllianceColor.BLUE);

        PlacementLevel placementLevel = getHubLevel(robot);

        if (placementLevel != PlacementLevel.NOT_FOUND) {
            robot.drive.moveRight_odometry(24);
            robot.control.removeDuck();
            robot.drive.moveForward_odometry(84);
            robot.drive.moveLeft_odometry(60);
            // robot.control.placeFreight(placementLevel);
            robot.drive.moveLeft_odometry(36);
            robot.drive.moveBackward_odometry(42);
            robot.drive.moveLeft_odometry(36);
        }

    }
}
