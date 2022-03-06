package org.firstinspires.ftc.teamcode.pp;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Util.Coordinate;

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
        Coordinate previousTarget = path.next();
        Coordinate currentTarget = path.next();
        double angleDelta = 0;

        while (true) {
            // Rotate towards the target
            if (currentTarget.distanceTo(robotModel.getCenter()) <= robotModel.getCircleRadius()) {
                previousTarget = currentTarget;
                currentTarget = path.next();
            }

            // Get point of intersection closest to the target point
            Coordinate trackPoint = getTrackPoint(previousTarget, currentTarget, robotModel.getCircleRadius());

            angleDelta = Math.atan((trackPoint.y - robotModel.getCenter().y) / (trackPoint.x - robotModel.getCenter().x));

            // Update robot position
            robotModel.rotate(angleDelta);
            robotModel.move();
        }
    }

    private Coordinate getTrackPoint(Coordinate start, Coordinate end, double r) {
        double slope = (start.y - end.y) / (start.x - end.x);
        double bias = start.y - slope * start.x;

        // Solve for x and y in
        // x^2 + y^2 = r^2, y = ax + b
        double x1 = ((-slope * bias) + Math.sqrt(slope*slope*r*r + r*r - bias*bias)) / (slope*slope + 1);
        double x2 = ((-slope * bias) - Math.sqrt(slope*slope*r*r + r*r - bias*bias)) / (slope*slope + 1);

        Coordinate c1 = new Coordinate(x1, slope*x1 + bias);
        Coordinate c2 = new Coordinate(x2, slope*x2 + bias);

        if (end.distanceTo(c1) > end.distanceTo(c2)) {
            return c2;
        }

        return c1;
    }
}
