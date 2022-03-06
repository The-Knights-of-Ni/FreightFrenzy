package org.firstinspires.ftc.teamcode.Util;

public class Coordinate {
    public double x;
    public double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Coordinate c) {
        return (this.x == c.x && this.y == c.y);
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public double distanceTo(Coordinate other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }
}
