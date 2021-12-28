package org.firstinspires.ftc.teamcode.Util.CoordinateSystem;

import org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class DriveInterface {

    Drive drive;

    public DriveInterface(Drive drive) {
        this.drive = drive;
    }

    public void move(Move move) {
//        this.drive.turnByAngle(move.getPower(), move.getAngle()); // Make angle correct
        this.drive.moveForward(move.getPower(), move.getDistance());
    }
}
