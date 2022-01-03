package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Auto.ScoreThread;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Control;
import org.firstinspires.ftc.teamcode.Subsystems.Control.BucketState;
import org.firstinspires.ftc.teamcode.Subsystems.Control.LidPosition;
import org.firstinspires.ftc.teamcode.Subsystems.Control.SlideState;
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

    private void initOpMode() throws IOException {
        // Initialize DC motor objects
        timer = new ElapsedTime();
        this.robot =  new Robot(this, hardwareMap, telemetry, timer, AllianceColor.BLUE, gamepad1, gamepad2,false);

        timeCurrent = timer.nanoseconds();
        timePre = timeCurrent;

        telemetry.addData("Waiting for start", "...");
        telemetry.update();
    }

    private void initDevices() {
        robot.control.setBucketState(BucketState.LEVEL);
        isBucketLevel = true;
        robot.control.setLidPosition(LidPosition.OPEN);
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

        initDevices();

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

            // Robot drive movement
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
                    robot.control.setIntakeDirection(true, true);
                    robot.control.setBucketState(BucketState.RAISED);
                    isBucketLevel = false;
                } else {
                    robot.control.setIntakeDirection(true, false);
                    robot.control.setBucketState(BucketState.LEVEL);
                    isBucketLevel = true;
                }
            }

            // Toggle slide up
            if (robot.aButton2 && !robot.isaButton2PressedPrev) {
                if(isIntakeOn) {
                    robot.control.setLidPosition(LidPosition.CLOSED);
                    new ScoreThread(robot, Control.PlacementLevel.TOP).start();
                    robot.control.setIntakeDirection(false, false);
                    isIntakeOn = false;
                }
                robot.control.setLidPosition(LidPosition.CLOSED);
            }

            // Toggle slide down
            if (robot.bButton2 && !robot.isbButton2PressedPrev) {
                robot.control.setLidPosition(LidPosition.CLOSED);
                robot.retract.start();
                sleep(4500); //NEEDS TO STAY (and be adjusted, or else lid will get caught on pulley)
                robot.control.setLidPosition(LidPosition.OPEN);
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

            // Toggle lid deployed/closed
            if(robot.bumperLeft2 && !robot.islBumper2PressedPrev) {
                robot.control.setLidPosition(LidPosition.CLOSED);
            }
            if(robot.bumperRight2 && !robot.isrBumper2PressedPrev) {
                robot.control.setLidPosition(LidPosition.DEPLOYED);
            }

            // TODO: Claw extend and retract (for marker)
        }
    }
}
