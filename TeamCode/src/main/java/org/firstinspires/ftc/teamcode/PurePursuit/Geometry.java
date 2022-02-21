package org.firstinspires.ftc.teamcode.PurePursuit;

public class Geometry {
    public static double distance(Coordinate first, Coordinate second) {
        return Math.sqrt((second.x - first.x)^2 + (second.y - first.y)^2);
    }
}
