package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Bucket Position Test")
public class TestBucketPosition extends LinearOpMode {

    private DcMotorEx bucket;
    private boolean isBucketMoving;

    @Override
    public void runOpMode() {

        bucket = (DcMotorEx) hardwareMap.dcMotor.get("bucket");
        bucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucket.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        while (opModeIsActive()) {

            // Toggle bucket up
            if (this.gamepad1.left_bumper) {
                if (isBucketMoving) {
                    bucket.setTargetPosition(-74);
                    bucket.setPower(1);
                    bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    isBucketMoving = false;
                } else {
//                    if(bucket.getCurrentPosition() == -74) {
//                        bucket.setPower(0);
//                        isBucketMoving = true;
//                    }
                    isBucketMoving = true;
                }
            }

            // Toggle bucket down
            if (this.gamepad1.right_bumper) {
                if (isBucketMoving) {
                    bucket.setTargetPosition(0);
                    bucket.setPower(1);
                    bucket.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    isBucketMoving = false;
                } else {
//                    if(bucket.getCurrentPosition() == 0) {
//                        bucket.setPower(0);
//                        isBucketMoving = true;
//                    }
                    isBucketMoving = true;
                }
            }

            telemetry.addData("Position", bucket.getCurrentPosition());
            telemetry.update();
        }
    }


}
