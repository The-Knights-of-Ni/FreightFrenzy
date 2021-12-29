package org.firstinspires.ftc.teamcode.Util.CoordinateSystem;

import java.util.ArrayList;

public class Mover extends Object2 {
    private final Coordinate center;

    public Mover(ArrayList<Coordinate> occupies, Coordinate center) {
        super(occupies);
        this.center = center;
    }
}
