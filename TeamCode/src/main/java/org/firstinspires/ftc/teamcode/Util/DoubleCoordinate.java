package org.firstinspires.ftc.teamcode.Util;

public class DoubleCoordinate {
    public double x;
    public double y;

    public DoubleCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(DoubleCoordinate c) {
        return (this.x == c.x && this.y == c.y);
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
