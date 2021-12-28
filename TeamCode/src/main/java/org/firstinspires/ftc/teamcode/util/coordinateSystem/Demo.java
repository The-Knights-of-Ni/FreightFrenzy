package org.firstinspires.ftc.teamcode.util.coordinateSystem;

import org.firstinspires.ftc.teamcode.util.coordinateSystem.file.fobj.fobj;
import org.firstinspires.ftc.teamcode.util.coordinateSystem.pathFinder.SimplePathFinder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A pathfinder demo Uses the Freight Frenzy board
 */
public class Demo {
    public Demo() throws IOException {
        Field field = new Field();
        Path path = new Path();
        ArrayList<Coordinate> allianceHubCoordinates = fobj.read("allianceHub.fobj");

        Object2 allianceHub = new Object2(allianceHubCoordinates);
        field.addObject(allianceHub);

        Coordinate start = new Coordinate(0, 0);
        path.add(start);
        SimplePathFinder pF = new SimplePathFinder();
        path.goTo(allianceHub, pF);
    }
}
