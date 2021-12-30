package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.teamcode.Robot;

public class ScoreThread extends Thread implements Runnable{
    private Robot robot;
    int placementLevel;

    public ScoreThread(Robot robot, int placementLevel) {
        this.robot = robot;
        this.placementLevel = placementLevel;
    }

    @Override
    public void run() {
        robot.control.setSlide(placementLevel);
    }

}
