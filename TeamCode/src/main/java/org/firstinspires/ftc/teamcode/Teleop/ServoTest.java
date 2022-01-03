package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

@TeleOp(name = "Servo Test", group= "Concept")
public class ServoTest extends LinearOpMode {
    private Robot robot;
    public ElapsedTime timer;
    double absIncrementStep = 0.005;

    private void initOpMode() throws IOException {
        telemetry.addData("Init Robot", "");
        telemetry.update();
        timer = new ElapsedTime();

        this.robot =  new Robot(this, hardwareMap, telemetry, timer, AllianceColor.BLUE, gamepad1, gamepad2,false);

        robot.telemetryBroadcast("wait for start", "");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            initOpMode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        waitForStart();
        robot.vision.stop();
        while(opModeIsActive()) {
            robot.getGamePadInputs();

            // Deploy
            if (robot.aButton && !robot.isaButtonPressedPrev) {
                robot.lid.setPosition(0.60);
            }
            // Open
            if (robot.bButton && !robot.isbButtonPressedPrev) {
                robot.lid.setPosition(0.5);
            }
            // Closed
            if(robot.xButton && !robot.isxButtonPressedPrev) {
                robot.lid.setPosition(0.75);
            }
        }
    }

}
