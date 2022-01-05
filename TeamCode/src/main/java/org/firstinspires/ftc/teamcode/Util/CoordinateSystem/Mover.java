package org.firstinspires.ftc.teamcode.Util.CoordinateSystem;

import java.util.ArrayList;

public class Mover {
    private Coordinate center;
    private final Path path;
    private final ArrayList<Coordinate> occupies;

    public Mover(ArrayList<Coordinate> occupies, Coordinate center, Path path) {
        this.occupies = occupies;
        this.center = center;
        this.path = path;
    }

    public void updatePath(Path path) {
        path.add(path);
    }

    public void updatePath(Coordinate coordinate) {
        path.add(coordinate);
    }

    public void move(Coordinate coordinate) {
        center = coordinate;
    }

    public Coordinate getCenter(Coordinate coordinate) {
        return this.center;
    }

    public boolean isOccupied(Coordinate coordinate) {
        return occupies.contains(coordinate);
    }
}
