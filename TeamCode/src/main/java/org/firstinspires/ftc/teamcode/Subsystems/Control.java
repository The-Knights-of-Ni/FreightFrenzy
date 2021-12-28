package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Control subsystem for controlling arms and claws
 */
public class Control extends Subsystem {
    // device declaration
    private final HardwareMap hardwareMap;
    private final LinearOpMode opMode;

    // DC Motors
    private DcMotorEx intake;
    private DcMotorEx bucket;
    private CRServo duckWheel;

    // Servos

    // Sensors
    private final BNO055IMU imu;

    public enum PlacementLevel {
        TOP,
        MIDDLE,
        BOTTOM
    }

    public Control(
            DcMotorEx intake,
            DcMotorEx bucket,
            CRServo duckWheel,
            BNO055IMU imu,
            LinearOpMode opMode,
            ElapsedTime timer) {
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

    // made custom servoRotation function because ftc devs are bad at their job.
    // Usage: the Direction incorporates the Servo class' Direction enum,
    // the Servo takes a Servo, TPM is how many times the pause should happen, aka the speed setting.
    // res is short for resolution, so we can determine how smooth or clunky we want the servo's
    // motions to be.

    public void setIntakeDirection(boolean status, boolean direction) { // simplified so only one method is needed for intake. status is true/false for on/off,
        double power = status ? 0.5 : 0; // direction is true/false for forward/reverse respectively.

        if (direction) {
            intake.setPower(power);
        } else {
            intake.setPower(-power);
        }
    }

    /**
     *
     * @param bucketState should be set to 0 for touching the floor, 1 for level, and 2 for raised.
     */

    public void setBucketState(int bucketState) {
        final int FLOOR = 0;
        final int LEVEL = -5;
        final int RAISED = -60;
        
        switch(bucketState) {
            case 0:
                //TOUCHING_FLOOR
                bucket.setTargetPosition(FLOOR);
                bucket.setPower(0.5);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                break;
            case 1:
                //LEVEL
                bucket.setTargetPosition(LEVEL);
                bucket.setPower(0.5);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                break;
            case 2:
                //RAISED
                bucket.setTargetPosition(RAISED);
                bucket.setPower(0.5);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            default:
                bucket.setTargetPosition(LEVEL);
                bucket.setPower(0.5);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        }

    }

    public void startCarousel(boolean direction) {
        duckWheel.set(direction ? 1 : -1);
    }

    public void stopCarousel() {
        duckWheel.set(0);
    }
}
