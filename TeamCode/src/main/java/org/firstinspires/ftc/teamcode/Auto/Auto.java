package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.DetectMarkerPipeline;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

/**
 * Auto creates a robots and runs it in auto mode.
 *
 * <p>Auto currently just initializes the Robot as Auto.runOpMode() is empty.</p>
 * <p>Tasks:</p>
 * <p>Deliver duck from carousel (10)</p>
 *
 * <p>Deliver freight to hub (6)</p>
 *
 * <p>   - deliver freight to corresponding level of custom element (20)</p>
 *
 * <p>Park in warehouse (10)</p>
 *
 * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
 */
@Autonomous(name = "Auto", group = "Concept")
@Disabled
public class Auto extends LinearOpMode {

    public static float mmPerInch = 25.4f;
    public Robot robot;

    /**
     * Override of runOpMode()
     *
     * <p>Please do not swallow the InterruptedException, as it is used in cases where the op mode
     * needs to be terminated early.
     *
     * @param allianceColor The alliance color
     * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
     */
    public void initAuto(AllianceColor allianceColor) throws IOException {
        ElapsedTime timer = new ElapsedTime();
        this.robot =  new Robot(this, timer, allianceColor);
    }

    public int getHubLevel() {
        int level = 0;
        DetectMarkerPipeline.MarkerLocation location = DetectMarkerPipeline.MarkerLocation.NOT_FOUND;
        do {
            location = robot.vision.detectMarkerRun();
        } while (location == DetectMarkerPipeline.MarkerLocation.NOT_FOUND);
        switch(location) {
            case LEFT:
                level = 1;
                break;
            case MIDDLE:
                level = 2;
                break;
            case RIGHT:
                level = 3;
                break;
        }
        return level;
    }

    @Override
    public void runOpMode() throws InterruptedException {
    }
}
