package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Control;
import org.firstinspires.ftc.teamcode.Subsystems.Control.LidPosition;
import org.firstinspires.ftc.teamcode.Subsystems.Control.SlideState;
import org.firstinspires.ftc.teamcode.Subsystems.Control.BucketState;
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
    private boolean isBucketLevel = false;
    private boolean isLidOpen = false;

    private void initOpMode() throws IOException {
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
    public void runOpMode() throws InterruptedException {
        try {
            initOpMode();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

            // Toggle bucket up-level
            if (robot.xButton && !robot.isxButtonPressedPrev) {
                if (isBucketLevel) {
                    robot.control.setBucketState(BucketState.RAISED);
                    isBucketLevel = false;
                } else {
                    robot.control.setBucketState(BucketState.LEVEL);
                    isBucketLevel = true;
                }
            }

            // Toggle bucket down-level
            if (robot.yButton && !robot.isyButtonPressedPrev) {
                if (isBucketLevel) {
                    robot.control.setBucketState(BucketState.FLOOR);
                    isBucketLevel = false;
                } else {
                    robot.control.setBucketState(BucketState.LEVEL);
                    isBucketLevel = true;
                }
            }

            // Toggle slide up
            if (robot.aButton2 && !robot.isaButton2PressedPrev) {
                robot.control.setSlide(SlideState.TOP);
            }
            // Toggle slide down
            if (robot.bButton2 && !robot.isbButton2PressedPrev) {
                robot.control.setSlide(SlideState.RETRACTED);
            }

            // Toggle duck wheel forward
            if (robot.xButton2 && !robot.isxButton2PressedPrev) {
                if (isDuckOn) {
                    robot.control.stopCarousel();
                    isDuckOn = false;
                } else {
                    robot.control.startCarousel(true);
                    isDuckOn = true;
                }
            }

            // Toggle duck wheel reverse
            if (robot.yButton2 && !robot.isyButton2PressedPrev) {
                if (isDuckOn) {
                    robot.control.stopCarousel();
                    isDuckOn = false;
                } else {
                    robot.control.startCarousel(false);
                    isDuckOn = true;
                }
            }

            if(robot.bumperLeft2 && !robot.islBumper2PressedPrev) {
                if(isLidOpen) {
                    robot.control.setLidPosition(LidPosition.CLOSED);
                    isLidOpen = false;
                } else {
                    robot.control.setLidPosition(LidPosition.OPEN);
                    isLidOpen = true;
                }
            }

            // TODO: Claw extend and retract (for marker)
        }
    }
}
