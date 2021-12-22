package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class TestBucketPosition extends LinearOpMode {

    private DcMotorEx bucket;
    private boolean isBucketMoving;

    @Override
    public void runOpMode() {

        bucket = (DcMotorEx) hardwareMap.dcMotor.get("bucket");
        bucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucket.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        while (opModeIsActive()) {

            // Toggle bucket up
            if (this.gamepad1.left_bumper) {
                if (isBucketMoving) {
                    bucket.setTargetPosition(bucket.getCurrentPosition() + 1);
                    isBucketMoving = false;
                } else {
                    isBucketMoving = true;
                }
            }

            // Toggle bucket down
            if (this.gamepad1.right_bumper) {
                if (isBucketMoving) {
                    bucket.setTargetPosition(bucket.getCurrentPosition() - 1);
                    isBucketMoving = false;
                } else {
                    isBucketMoving = true;
                }
            }


            telemetry.addData("Position", bucket.getCurrentPosition());
        }
    }


}
