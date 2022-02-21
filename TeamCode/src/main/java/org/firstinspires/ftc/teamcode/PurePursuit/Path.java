package org.firstinspires.ftc.teamcode.PurePursuit;

public class Path {
    public LinearPath linearPath;
    public Path(LinearPath path) {
        linearPath = path;
    }

    public void reset(Coordinate coordinate) {
        linearPath.reset(coordinate);
    }
}
