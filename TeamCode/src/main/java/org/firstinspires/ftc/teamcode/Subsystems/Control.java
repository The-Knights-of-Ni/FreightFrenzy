package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
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
    private final DcMotorEx intake;
    private final DcMotorEx bucket;
    private final DcMotorEx slide;
    private final CRServo duckWheel;
    private final ServoEx lid;

    // Servos

    // Sensors
    private final BNO055IMU imu;

    public enum PlacementLevel {
        TOP,
        MIDDLE,
        BOTTOM,
        NOT_FOUND
    }

    public enum BucketState {
        FLOOR,
        LEVEL,
        RAISED
    }

    public enum SlideState {
        RETRACTED,
        BOTTOM,
        MIDDLE,
        TOP
    }

    public enum LidPosition {
        CLOSED,
        DEPLOYED,
        OPEN
    }

    public Control(
            DcMotorEx intake,
            DcMotorEx bucket,
            DcMotorEx slide,
            CRServo duckWheel,
            BNO055IMU imu,
            LinearOpMode opMode,
            ElapsedTime timer,
            ServoEx lid) {
        super(opMode.telemetry, opMode.hardwareMap, timer);

        // store device information locally

        this.intake = intake;
        this.bucket = bucket;
        this.slide = slide;
        this.duckWheel = duckWheel;
        this.opMode = opMode;
        this.hardwareMap = opMode.hardwareMap;
        this.imu = imu;
        this.lid = lid;
        this.timer = timer;
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Sets all drive motors to specified zero power behavior
     */
    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior mode) {
        intake.setZeroPowerBehavior(mode);
        bucket.setZeroPowerBehavior(mode);
        slide.setZeroPowerBehavior(mode);
    }

    public void setIntakeDirection(boolean status, boolean direction) { // simplified so only one method is needed for intake. status is true/false for on/off,
        double power = status ? 0.5 : 0; // direction is true/false for forward/reverse respectively.

        if (direction) {
            intake.setPower(power);
        } else {
            intake.setPower(-power);
        }
    }

    public void setBucketState(BucketState bucketState) {
        final int FLOOR = 0;
        final int LEVEL = -5;
        final int RAISED = -65;

        switch (bucketState) {
            case FLOOR:
                bucket.setTargetPosition(FLOOR);
                bucket.setPower(0.1);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                break;
            case LEVEL:
                bucket.setTargetPosition(LEVEL);
                bucket.setPower(0.5);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                break;
            case RAISED:
                bucket.setTargetPosition(RAISED);
                bucket.setPower(1.0);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                break;
        }
    }

    public void setSlide(SlideState slideState) {
        final int RETRACTED = 0;
        final int BOTTOM = -481;
        final int MIDDLE = -758;
        final int TOP = -1292;

        switch (slideState) {
            case RETRACTED:
                slide.setTargetPosition(RETRACTED);
                slide.setPower(0.5);
                slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                break;
            case BOTTOM:
                slide.setTargetPosition(BOTTOM);
                slide.setPower(0.5);
                slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                break;
            case MIDDLE:
                slide.setTargetPosition(MIDDLE);
                slide.setPower(0.5);
                slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            case TOP:
                slide.setTargetPosition(TOP);
                slide.setPower(0.5);
                slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        }
    }

    public void setLidPosition(LidPosition position) {
        // TODO: find the lid position constants
        final double DEPLOYED = 0.5;
        final double OPEN = 1;
        final double CLOSED = 0;

        switch(position) {
            case CLOSED:
                //CLOSED
                lid.setPosition(CLOSED);
                break;
            case DEPLOYED:
                //DEPLOYED
                lid.setPosition(DEPLOYED);
                break;
            case OPEN:
                //OPEN
                lid.setPosition(OPEN);
                break;
        }
    }


    public void startCarousel(boolean direction) {
        duckWheel.set(direction ? 1 : -1);
    }

    public void stopCarousel() {
        duckWheel.set(0);
    }
}