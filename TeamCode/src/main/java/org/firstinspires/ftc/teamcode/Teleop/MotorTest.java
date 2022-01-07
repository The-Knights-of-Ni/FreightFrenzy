package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Motor Position Test")
public class MotorTest extends LinearOpMode {
    @Override
    public void runOpMode() {

        DcMotorEx bucket = (DcMotorEx) hardwareMap.dcMotor.get("bucket");
        bucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucket.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        DcMotorEx slide = (DcMotorEx) hardwareMap.dcMotor.get("slide");
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Position (bucket)", bucket.getCurrentPosition());
            telemetry.addData("Position (slide)", slide.getCurrentPosition());
            telemetry.update();
        }
    }


}
