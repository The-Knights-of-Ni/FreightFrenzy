package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.teamcode.Robot;

public class ScoreThread extends Thread implements Runnable{
    private Robot robot;
    boolean direction;
    int placementLevel;
    final int RETRACTED = 0;
    final int BOTTOM = -481;
    final int MIDDLE = -758;
    final int TOP = -1292;

    public ScoreThread(Robot robot, int placementLevel) {
        this.robot = robot;
        this.placementLevel = placementLevel;
    }

    @Override
    public void run() {
        robot.control.setSlide(placementLevel);
    }

}
