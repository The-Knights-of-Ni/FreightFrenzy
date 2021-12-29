package org.firstinspires.ftc.teamcode.util.coordinateSystem;

import org.firstinspires.ftc.teamcode.util.coordinateSystem.pathFinder.PathFinder;

import java.util.ArrayList;

public class Object2 {    private final ArrayList<Coordinate> occupies;

    public Object2(ArrayList<Coordinate> occupies) {
        this.occupies = occupies;
    }

    public ArrayList<Coordinate> getOccupies() {
        return this.occupies;
    }

    public Coordinate getClosestCoordinate(Coordinate current, PathFinder pF) {
        int minLength = Integer.MAX_VALUE;
        Coordinate minCoordinate = occupies.get(0);
        Path shortestPath;
        for (Coordinate coordinate : this.occupies) {
            shortestPath = pF.getShortestPath(current, coordinate);
            if (shortestPath.length() < minLength) {
                minLength = shortestPath.length();
                minCoordinate = coordinate;
            }
        }
        return minCoordinate;
    }
}
