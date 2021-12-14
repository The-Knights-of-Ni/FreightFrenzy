package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;

@TeleOp(name = "TestServo")
public class TestServo extends LinearOpMode {
  private Robot robot;

  @Override
  public void runOpMode() throws InterruptedException {
    ElapsedTime timer = new ElapsedTime();
    try {
      robot = new Robot(this, timer, AllianceColor.BLUE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    waitForStart();
    robot.control.rotateCarousel();
  }
}
