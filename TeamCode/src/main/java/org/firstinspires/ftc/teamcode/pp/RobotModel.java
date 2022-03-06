package org.firstinspires.ftc.teamcode.pp;

import org.firstinspires.ftc.teamcode.Util.Coordinate;

public class RobotModel {
    private double circleRadius = 1.0;
    private double speed;
    private double angle;
    private Coordinate center;
    public RobotModel(Coordinate start) {
        center = start;
    }

    public void displace(Coordinate displacement) {
        center.x += displacement.x;
        center.y += displacement.y;
    }

    public void move() {
        center.x += speed * Math.cos(angle);
        center.y += speed * Math.sin(angle);
    }

    public void rotate(double angle) {
        this.angle += angle;
    }

    public Coordinate getCenter() {
        return center;
    }

    public double getCircleRadius() {
        return circleRadius;
    }

}
