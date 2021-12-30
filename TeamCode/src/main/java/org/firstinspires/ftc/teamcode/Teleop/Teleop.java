package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
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

    private void initOpMode() {
        // Initialize DC motor objects
        timer = new ElapsedTime();
        robot = new Robot(this, timer, AllianceColor.BLUE);

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
     * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
     */
    @Override
    public void runOpMode() {
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
            if (robot.xButton2 && !robot.isxButton2PressedPrev) {
                robot.control.setBucketState(2);
            }

            // Toggle bucket down
            if (robot.yButton2 && !robot.isyButton2PressedPrev) {
                robot.control.setBucketState(0);
            }

            // Toggle slide up
            if (robot.aButton2 && !robot.isaButton2PressedPrev) {
                robot.control.setSlide(3);
            }
            // Toggle slide down
            if (robot.bButton2 && !robot.isbButton2PressedPrev) {
                robot.control.setSlide(0);
            }
        }
    }
}
