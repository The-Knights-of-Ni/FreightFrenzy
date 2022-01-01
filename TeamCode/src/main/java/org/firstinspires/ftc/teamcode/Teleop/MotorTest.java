package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Motor Position Test")
public class MotorTest extends LinearOpMode {

    private DcMotorEx bucket;
    private DcMotorEx slide;
    private boolean isBucketMoving;

    @Override
    public void runOpMode() {

        bucket = (DcMotorEx) hardwareMap.dcMotor.get("bucket");
        bucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucket.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        slide = (DcMotorEx) hardwareMap.dcMotor.get("slide");
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        bucket.setTargetPosition(-5);
        bucket.setPower(0.5);
        bucket.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();
        while (opModeIsActive()) {
            if(this.gamepad1.a) {
                bucket.setTargetPosition(-68);
                bucket.setPower(0.5);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            }
            if(this.gamepad1.b) {
                bucket.setTargetPosition(0);
                bucket.setPower(0.5);
                bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            }
            telemetry.addData("Position (bucket)", bucket.getCurrentPosition());
            telemetry.addData("Position (slide)", slide.getCurrentPosition());
            telemetry.update();
        }
    }


}
