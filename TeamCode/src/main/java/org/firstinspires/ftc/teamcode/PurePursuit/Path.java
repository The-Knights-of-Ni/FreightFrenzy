package org.firstinspires.ftc.teamcode.PurePursuit;

import java.util.ArrayList;

public class Path {
    public final ArrayList<Coordinate> path;
    private int current = 0;
    public Path(ArrayList<Coordinate> path) {
        this.path = path;
    }

    public Coordinate next() {
        Coordinate next = path.get(current);
        current ++;
        return next;
    }
}
