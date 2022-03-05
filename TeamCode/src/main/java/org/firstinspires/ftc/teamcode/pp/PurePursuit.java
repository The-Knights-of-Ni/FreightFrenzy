package org.firstinspires.ftc.teamcode.pp;

public class PurePursuit implements Runnable {
    private Field field;
    private RobotModel robotModel;
    private LinearPath path;

    public PurePursuit(Field field, RobotModel robotModel, LinearPath path) {
        this.field = field;
        this.robotModel = robotModel;
        this.path = path;
    }

    @Override
    public void run() {
        while (true) {
            // TODO: Shenanigans
        }
    }
}
