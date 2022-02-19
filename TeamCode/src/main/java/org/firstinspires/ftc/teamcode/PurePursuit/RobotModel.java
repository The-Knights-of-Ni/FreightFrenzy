package org.firstinspires.ftc.teamcode.PurePursuit;

public class RobotModel {
    private double circleRadius = 1.0;
    private Coordinate center;
    public RobotModel(Coordinate start) {
        center = start;
    }

    public void move(Coordinate position) {
        center = position;
    }

    public Coordinate getCenter() {
        return center;
    }
}
