package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Control.SlideState;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;

public class ScoreThread extends Thread{
    private Robot robot;
    PlacementLevel placementLevel;
    Telemetry telemetry;
    final int RETRACTED = 0;
    final int BOTTOM = -481;
    final int MIDDLE = -758;
    final int TOP = -1292;

    public ScoreThread(Robot robot, PlacementLevel placementLevel, Telemetry telemetry) {
        this.robot = robot;
        this.placementLevel = placementLevel;
        this.telemetry = telemetry;
    }

    @Override
    public void run() {
        try {
            telemetry.addData("Thread Status", "Running");
            telemetry.update();
            switch (placementLevel) {
                case TOP:
                    robot.control.setSlide(SlideState.TOP);
                    break;
                case MIDDLE:
                    robot.control.setSlide(SlideState.MIDDLE);
                    break;
                case BOTTOM:
                    robot.control.setSlide(SlideState.BOTTOM);
                    break;
            }
        } catch (Exception e) {
            telemetry.addData("Thread Status", "Interrupted");
            telemetry.update();
        }
    }

}
