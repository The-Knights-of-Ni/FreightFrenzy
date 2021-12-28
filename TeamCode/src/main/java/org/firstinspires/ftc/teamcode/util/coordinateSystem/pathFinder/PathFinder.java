package org.firstinspires.ftc.teamcode.util.coordinateSystem.pathFinder;

import org.firstinspires.ftc.teamcode.util.coordinateSystem.Coordinate;
import org.firstinspires.ftc.teamcode.util.coordinateSystem.Path;

public interface PathFinder {
    Path getShortestPath(Coordinate start, Coordinate end);
}
