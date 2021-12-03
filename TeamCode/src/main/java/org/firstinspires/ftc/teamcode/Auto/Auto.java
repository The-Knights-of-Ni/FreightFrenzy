package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;


/**
 * Auto creates a robots and runs it in auto mode.
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
@Autonomous(name = "Auto", group = "Concept")
public class Auto extends LinearOpMode {
    /**
     * Override of runOpMode()
     *
     * <p>Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.</p>
     *
     * @param allianceColor The alliance color
     * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
     * @return
     */

    public Robot init(AllianceColor allianceColor) throws IOException {
        ElapsedTime timer = new ElapsedTime();
        return new Robot(this, timer, true);
    }

    public int getHubLevel() {
//        int placementLevel;
//        switch (finalMarkerLocation) {
//            case LEFT:
//                placementLevel = 1;
//                break;
//            case MIDDLE:
//                placementLevel = 2;
//                break;
//            case RIGHT:
//                placementLevel = 3;
//                break;
//            default:
//                placementLevel = -1;
//                break;
//        }
//        return placementLevel;
        return -1;
    }

    @Override
    public void runOpMode() throws InterruptedException {
    }
}
