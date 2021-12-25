package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.DetectMarkerPipeline;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

@TeleOp(name = "Teleop")
public class Teleop extends LinearOpMode {
    double deltaT;
    double timeCurrent;
    double timePre;
    ElapsedTime timer;
    private Robot robot;
    private double robotAngle;
    private boolean isIntakeOn = false;
    private boolean isDuckOn = false;
    private boolean isBucketMoving = false;

    private void initOpMode() {
        // Initialize DC motor objects
        timer = new ElapsedTime();
        try {
            robot = new Robot(this, timer, AllianceColor.BLUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeCurrent = timer.nanoseconds();
        timePre = timeCurrent;

        telemetry.addData("Waiting for start", "...");
        telemetry.update();
    }

    /**
     * Override of runOpMode()
     *
     * <p>Please do not swallow the InterruptedException, as it is used in cases where the op mode
     * needs to be terminated early.
     *
     * @throws InterruptedException
     * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
     */
    @Override
    public void runOpMode() throws InterruptedException {
        initOpMode();

        ElapsedTime timer = new ElapsedTime();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        telemetry.clearAll();
        timeCurrent = timer.nanoseconds();
        timePre = timeCurrent;

        while (opModeIsActive()) { // clearer nomenclature for variables
            robot.getGamePadInputs();

            timeCurrent = timer.nanoseconds();
            deltaT = timeCurrent - timePre;
            timePre = timeCurrent;

            double[] motorPowers;
            robotAngle = robot.imu.getAngularOrientation().firstAngle;
            motorPowers =
                    robot.drive.calcMotorPowers(robot.leftStickX, robot.leftStickY, robot.rightStickX);
            robot.drive.setDrivePowers(motorPowers);

            // Toggle intake regular
            if (robot.aButton && !robot.isaButtonPressedPrev) {
                if (isIntakeOn) {
                    robot.control.setIntakeDirection(false, true);
                    isIntakeOn = false;
                } else {
                    robot.control.setIntakeDirection(true, true);
                    isIntakeOn = true;
                }
            }

            // Toggle intake reverse
            if (robot.bButton && !robot.isbButtonPressedPrev) {
                if (isIntakeOn) {
                    robot.control.setIntakeDirection(false, false);
                    isIntakeOn = false;
                } else {
                    robot.control.setIntakeDirection(true, false);
                    isIntakeOn = true;
                }
            }

            // Toggle duck wheel forward
            if (robot.yButton && !robot.isyButtonPressedPrev) {
                if (isDuckOn) {
                    robot.control.stopCarousel();
                    isDuckOn = false;
                } else {
                    robot.control.startCarousel(true);
                    isDuckOn = true;
                }
            }

            // Toggle duck wheel reverse
            if (robot.xButton && !robot.isxButtonPressedPrev) {
                if (isDuckOn) {
                    robot.control.stopCarousel();
                    isDuckOn = false;
                } else {
                    robot.control.startCarousel(false);
                    isDuckOn = true;
                }
            }

            // Toggle bucket up
            if (robot.bumperLeft && !robot.islBumperPressedPrev) {
                robot.control.setBucketState(2);
            }

            // Toggle bucket down
            if (robot.bumperRight && !robot.isrBumperPressedPrev) {
                robot.control.setBucketState(0);
            }
        }
    }
}
