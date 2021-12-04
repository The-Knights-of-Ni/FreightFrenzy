package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.DetectMarkerPipeline;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

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
    private boolean isDuckOn = false;
    private boolean isBucketMoving = false;

    private void initOpMode() {
        //Initialize DC motor objects
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

            DetectMarkerPipeline.MarkerLocation where = robot.vision.detectMarkerRun();
            // telemetry.addData("Marker Location", "", where);
            // telemetry.update();

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
                    robot.control.setIntakeDirection(false, true);
                    isIntakeOn = false;
                } else {
                    robot.control.setIntakeDirection(true, true);
                    isIntakeOn = true;
                }
            }

            //Toggle intake reverse
            if (robot.bButton && !robot.isbButtonPressedPrev) {
                if (isIntakeOn) {
                    robot.control.setIntakeDirection(false, false);
                    isIntakeOn = false;
                } else {
                    robot.control.setIntakeDirection(true, false);
                    isIntakeOn = true;
                }
            }

            //Toggle bucket up
            if (robot.bumperLeft) {
                if(isBucketMoving) {
                    robot.control.setBucketDirection(false, true);
                    isBucketMoving = false;
                } else {
                    robot.control.setBucketDirection(true, true);
                    isBucketMoving = true;
                }
            }

            //Toggle bucket down
            if (robot.bumperRight) {
                if(isBucketMoving) {
                    robot.control.setBucketDirection(false, false);
                    isBucketMoving = false;
                } else {
                    robot.control.setBucketDirection(true, false);
                    isBucketMoving = true;
                }
            }

            if (robot.xButton && !robot.isxButtonPressedPrev) {
                if (isDuckOn) {
                    robot.control.toggleDuckWheel(false);
                    isDuckOn = false;
                } else {
                    robot.control.toggleDuckWheel(true);
                    isDuckOn = true;
                }
            }
        }
    }
}