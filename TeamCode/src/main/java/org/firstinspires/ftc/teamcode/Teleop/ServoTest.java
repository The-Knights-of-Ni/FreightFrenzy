package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

@TeleOp(name = "Servo Test", group= "Concept")
public class ServoTest extends LinearOpMode {
    private Robot robot;
    public ElapsedTime timer;

    private void initOpMode() throws IOException {
        telemetry.addData("Init Robot", "");
        telemetry.update();
        timer = new ElapsedTime();

        this.robot = new Robot(this, timer, AllianceColor.BLUE);

        robot.telemetryBroadcast("wait for start", "");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        ServoEx servo = (ServoEx) hardwareMap.servo.get("lid");
        int position = 0;

        while (opModeIsActive()) {
            servo.setPosition(position);
            if(robot.aButton) {position += 0.01;}
            else if(robot.bButton) {position -= 0.01;}
            robot.telemetryBroadcast("Position", String.valueOf(position));
        }
    }

}
