package org.firstinspires.ftc.teamcode.Tests.TeleopTests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/** made by Alessandro Bonecchi on 10.23.2021 **/

@TeleOp(name = "Servo Test")
@Disabled
public class ServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.servo.get("servo1");
        double servoPos = 0.0;
        final double servoMin = 0.0;
        final double servoMax = 1.0;
        final double speed = 0.01;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) { // clearer nomenclature for variables
            boolean aBtn = gamepad1.a;
            boolean bBtn = gamepad1.b;
            if (aBtn) {
                if (servoPos < servoMax) {
                    if (servoPos > servoMin) {
                        servoPos += speed;
                        sleep(10);
                    }
                }
            }
            if (bBtn) {
                if (servoPos < servoMax) {
                    if (servoPos > servoMin) {
                        servoPos -= speed;
                        sleep(10);
                    }
                }
            }
            servo.setPosition(servoPos);
        }
    }
}
