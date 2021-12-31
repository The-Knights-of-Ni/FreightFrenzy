package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Control.SlideState;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;
import org.firstinspires.ftc.teamcode.Subsystems.Control.LidPosition;

public class ScoreThread extends Thread{
    private Robot robot;
    PlacementLevel placementLevel;
    Telemetry telemetry;

    public ScoreThread(Robot robot, PlacementLevel placementLevel, Telemetry telemetry) {
        this.robot = robot;
        this.placementLevel = placementLevel;
        this.telemetry = telemetry;
    }

    @Override
    public void run() {
        try {
            robot.telemetryBroadcast("Thread Status", "Running");
            robot.control.setLidPosition(LidPosition.CLOSED);
            switch (placementLevel) {
                case TOP:
                    robot.control.setSlide(SlideState.TOP);
                    robot.control.setLidPosition(LidPosition.DEPLOYED);
                    break;
                case MIDDLE:
                    robot.control.setSlide(SlideState.MIDDLE);
                    robot.control.setLidPosition(LidPosition.DEPLOYED);
                    break;
                case BOTTOM:
                    robot.control.setSlide(SlideState.BOTTOM);
                    robot.control.setLidPosition(LidPosition.DEPLOYED);
                    break;
            }
        } catch (Exception e) {
            telemetry.addData("Thread Status", "Interrupted " + e);
            telemetry.update();
        }
    }

}
