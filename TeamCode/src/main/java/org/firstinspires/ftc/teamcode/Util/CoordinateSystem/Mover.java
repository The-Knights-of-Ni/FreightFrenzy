package org.firstinspires.ftc.teamcode.Util.CoordinateSystem;

import java.util.ArrayList;

public class Mover {
    private final Coordinate center;
    private Path path;
    private final ArrayList<Coordinate> occupies;

    public Mover(ArrayList<Coordinate> occupies, Coordinate center, Path path) {
        this.occupies = occupies;
        this.center = center;
        this.path = path;
    }
}
