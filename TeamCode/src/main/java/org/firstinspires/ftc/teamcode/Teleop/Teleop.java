package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

import java.io.IOException;

@TeleOp(name = "Teleop")
public class Teleop extends LinearOpMode {
    private Robot robot;

    double deltaT;
    double timeCurrent;
    double timePre;

    ElapsedTime timer;

    private double robotAngle;
    private boolean isIntakeOn = false;
    private boolean isBucketMoving = false;

    private void initOpMode() {
        //Initialize DC motor objects
        timer = new ElapsedTime();
        try {
            robot = new Robot(this, timer, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeCurrent = timer.nanoseconds();
        timePre = timeCurrent;

        telemetry.addData("Waiting for start", "...");
        telemetry.update();
    }

    /** Override of runOpMode()
     *
     * <p>Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.</p>
     *
     * @throws InterruptedException
     *
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
            motorPowers = robot.drive.calcMotorPowers(robot.leftStickX, robot.leftStickY, robot.rightStickX);
            robot.drive.setDrivePowers(motorPowers);

            robot.drive.setDrivePowers(motorPowers);

            //Toggle intake regular
            if (robot.aButton && !robot.isaButtonPressedPrev) {
                if (isIntakeOn) {
                    robot.main.setIntakeDirection(false, true);
                    isIntakeOn = false;
                } else {
                    robot.main.setIntakeDirection(true, true);
                    isIntakeOn = true;
                }
            }

            //Toggle intake reverse
            if (robot.bButton && !robot.isbButtonPressedPrev) {
                if (isIntakeOn) {
                    robot.main.setIntakeDirection(false, false);
                    isIntakeOn = false;
                } else {
                    robot.main.setIntakeDirection(true, false);
                    isIntakeOn = true;
                }
            }

            //Toggle bucket up
           /* if (robot.gamePadConfig.bumperLeft && !robot.gamePadConfig.isleftBumperPressedPrev){
                if(isBucketMoving) {
                    robot.control.setIntakeDirection(true);
                    isBucketMoving = false;
                } else {
                    robot.control.setIntakeDirection(true);
                    isBucketMoving = true;
                }
            }
            //Toggle bucket down
            if (robot.gamePadConfig.bumperRight && !robot.gamePadConfig.isrightBumperPressedPrev){
                if(isBucketMoving) {
                    robot.control.setIntakeDirection(false);
                    isBucketMoving = false;
                } else {
                    robot.control.setIntakeDirection(false);
                    isBucketMoving = true;
                }
            } */
        }
    }
}