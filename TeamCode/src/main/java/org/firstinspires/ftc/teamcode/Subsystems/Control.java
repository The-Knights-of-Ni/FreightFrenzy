package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Control subsystem for controlling arms and claws
 */
public class Control extends Subsystem {

    // DC Motors
    private final DcMotorEx intake;
    private final DcMotorEx bucket;
    private final DcMotorEx slide;
    private final ServoEx lid;

    // Servos
    private final CRServo duckWheel;

    // Enums store semantic position of each motor along with motor constants in ticks when relevant
    public enum PlacementLevel {
        TOP,
        MIDDLE,
        BOTTOM,
        NOT_FOUND
    }

    public enum BucketState {
        FLOOR(0, 0.1),
        LEVEL(-5, 0.5),
        RAISED(-65, 1);

        public final double power;
        public final int position;

        BucketState(int position, double power) {
            this.position = position;
            this.power = power;
        }
    }

    public enum SlideState {
        RETRACTED(0, 0.2),
        BOTTOM(-481, 0.2),
        MIDDLE(-758, 0.2),
        TOP(-1292, 0.2);

        public final int position;
        public final double power;

        SlideState(int position, double power) {
            this.position = position;
            this.power = power;
        }
    }

    public enum LidPosition {
        CLOSED(0),
        DEPLOYED(0.5),
        OPEN(1);

        public final double position;

        LidPosition(double position) {
            this.position = position;
        }
    }

    public Control(DcMotorEx intake, DcMotorEx bucket, DcMotorEx slide, CRServo duckWheel, ServoEx lid, BNO055IMU imu,
                   Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer) {
        super(telemetry, hardwareMap, timer);

        // Store device information locally
        this.intake = intake;
        this.bucket = bucket;
        this.slide = slide;
        this.duckWheel = duckWheel;
        this.lid = lid;
        this.timer = timer;

        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Set all DC motors to specified zero power behavior
     */
    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior mode) {
        intake.setZeroPowerBehavior(mode);
        bucket.setZeroPowerBehavior(mode);
        slide.setZeroPowerBehavior(mode);
    }

    /**
     * Toggle the intake and set its direction
     *
     * @param status    Indicates whether to turn the motor on (true) or off (false).
     * @param direction Specifies the direction to turn, where true/false corresponds to forward/reverse respectively.
     */
    public void setIntakeDirection(boolean status, boolean direction) {
        double power = status ? 0.5 : 0;

        if (direction) {
            intake.setPower(power);
        } else {
            intake.setPower(-power);
        }
    }

    /**
     * Set the position of the bucket
     *
     * @param bucketState   the bucket location. Must be either FLOOR, LEVEL, or RAISED.
     */
    public void setBucketState(BucketState bucketState) {
        bucket.setTargetPosition(bucketState.position);
        bucket.setPower(bucketState.power);
        bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    /**
     * Set the position of the slide
     *
     * @param slideState the position to set the slide. Must be either RETRACTED, BOTTOM, MIDDLE, or TOP.
     */
    public void setSlide(SlideState slideState) {
        slide.setPower(slideState.power);
        slide.setTargetPosition(slideState.position);
        slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    /**
     * Set the position of the lid to drop the freight
     *
     * @param lidPosition   the position to set the lid. Must be either CLOSED, DEPLOYED, or OPEN.
     */
    public void setLidPosition(LidPosition lidPosition) {
        lid.setPosition(lidPosition.position);
    }

    /**
     * Start the duck wheel in order to spin the carousel
     *
     * @param direction the rotation direction. True/false corresponds to forward and backwards respectively.
     */
    public void startCarousel(boolean direction) {
        duckWheel.set(direction ? 1 : -1);
    }

    /**
     * Stop the carousel from rotating
     */
    public void stopCarousel() {
        duckWheel.set(0);
    }
}