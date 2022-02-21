package org.firstinspires.ftc.teamcode.PurePursuit;

import java.util.ArrayList;

public class LinearPath {
    public ArrayList<Coordinate> path;
    private int current = 0;

    public LinearPath() {
        this.path = new ArrayList<>();
    }

    public LinearPath(ArrayList<Coordinate> path) {
        this.path = path;
    }

    public Coordinate next() {
        Coordinate next = path.get(current);
        current ++;
        return next;
    }

    public void addCoordinate(Coordinate c) {
        path.add(c);
    }

    public void addCoordinate(int index, Coordinate c) {
        path.add(index, c);
    }

    public void goTo(GameObject o) {
        ArrayList<Coordinate> reach = o.getCoordinates();
        ArrayList<Double> distances = new ArrayList<>();
        for (Coordinate c: reach) {
            distances.add(Geometry.distance(path.get(path.size()-1), c));
        }
    }
}
