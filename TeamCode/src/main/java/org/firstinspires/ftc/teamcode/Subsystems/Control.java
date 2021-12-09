package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Control subsystem for controlling arms and claws
 * Created by AndrewC on 1/17/2020
 * Cleaned up by Alessandro Bonecchi
 */
public class Control extends Subsystem {
    // device declaration
    private HardwareMap hardwareMap;
    private LinearOpMode opMode;

    //DC Motors
    private DcMotorEx intake;
    private DcMotorEx bucket;
    private Servo duckWheel;

    //Servos

    //Sensors
    private BNO055IMU imu;

    public Control(DcMotorEx intake, DcMotorEx bucket, Servo duckWheel, BNO055IMU imu, LinearOpMode opMode, ElapsedTime timer) {
        super(opMode.telemetry, opMode.hardwareMap, timer);

        // store device information locally

        this.intake = intake;
        this.bucket = bucket;
        this.duckWheel = duckWheel;
        this.opMode = opMode;
        this.hardwareMap = opMode.hardwareMap;
        this.imu = imu;
        this.timer = timer;
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Sets all drive motors to specified zero power behavior
     */
    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior mode) {
        intake.setZeroPowerBehavior(mode);
        bucket.setZeroPowerBehavior(mode);
    }

    //made custom servoRotation function because ftc devs are bad at their job.
    //Usage: the Direction incorporates the Servo class' Direction enum,
    //the Servo takes a Servo, TPM is how many times the pause should happen, aka the speed setting.
    public void setServoRotation(boolean status, Servo.Direction direction, Servo servo, int TPM, double res) {
        if(status) {
            servo.setDirection(direction);
            double servoPos = 0;
            while (opMode.opModeIsActive()) {
                servo.setPosition(servoPos);
                if (direction == Servo.Direction.FORWARD) {
                    servoPos += res;
                } else {
                    servoPos -= res;
                }
                opMode.sleep((long) ((60 / TPM) * 1000));
            }
        } else {
            servo.setPosition(0);
        }
    }

    public void setIntakeDirection(boolean status, boolean direction) {      // simplified so only one method is needed for intake. status is true/false for on/off,
        double power = status ? 0.5 : 0;                                          // direction is true/false for forward/reverse respectively.

        if(direction) {
            intake.setPower(power);
        } else {
            intake.setPower(-power);
        }
    }

    public void setBucketDirection(boolean status, boolean direction) { // Usage similar to the setIntakeDirection function.
        int power = status ? 1 : 0;

        if (direction) {
            bucket.setPower(power);
        } else {
            bucket.setPower(-power);
        }
    }

    public void toggleDuckWheel(boolean status) {      // simplified so only one method is needed for intake. status is true/false for on/off,
        setServoRotation(true, Servo.Direction.FORWARD, duckWheel, 60, 0.001);
    }

}
