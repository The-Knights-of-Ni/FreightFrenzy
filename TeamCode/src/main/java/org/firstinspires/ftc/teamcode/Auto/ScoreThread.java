package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;

public class ScoreThread extends Thread{
    private Robot robot;
    int placementLevel;
    Telemetry telemetry;
    final int RETRACTED = 0;
    final int BOTTOM = -481;
    final int MIDDLE = -758;
    final int TOP = -1292;

    public ScoreThread(Robot robot, int placementLevel, Telemetry telemetry) {
        this.robot = robot;
        this.placementLevel = placementLevel;
        this.telemetry = telemetry;
    }

    @Override
    public void run() {
        try {
            telemetry.addData("Thread Status", "Running");
            telemetry.update();
            robot.control.setSlide(placementLevel);
        } catch (Exception e) {
            telemetry.addData("Thread Status", "Interrupted");
            telemetry.update();
        }
    }

}
