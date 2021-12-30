package org.firstinspires.ftc.teamcode.Util.CoordinateSystem;

import java.util.ArrayList;

/**
 * This is the Playing Field The objects are baked into the field for speed
 */
public class Field {
    public final int length; // length
    public final int width; // width
    ArrayList<FieldObject> fieldObjects = new ArrayList<>(); // List of objects
    private final int[][] field; // Objects are baked here

    public Field(int length, int width) {
        this.length = length;
        this.width = width;
        this.field = new int[length][width];
        int x;
        int y;
        for (x = 0; x < length; x++) {
            for (y = 0; y < width; y++) {
                this.field[x][y] = 0;
            }
        }
    }

    public Field(int length, int width, ArrayList<FieldObject> fieldObjects) {
        this.length = length;
        this.width = width;
        this.field = new int[length][width];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                this.field[x][y] = 0;
            }
        }
        this.fieldObjects = fieldObjects;
        for (FieldObject fieldObject : fieldObjects) {
            for (Coordinate coordinate : fieldObject.getOccupies()) {
                this.field[coordinate.getX()][coordinate.getY()] = 1;
            }
        }
    }

    public boolean isBlocked(Coordinate goTo) {
        return field[goTo.getX()][goTo.getY()] == -1;
    }

    public void addObject(FieldObject fieldObject) {
        for (Coordinate coordinate : fieldObject.getOccupies()) {
            this.field[coordinate.getX()][coordinate.getY()] = 1;
        }
        fieldObjects.add(fieldObject);
    }

    public int get(Coordinate coordinate) {
        return field[coordinate.getX()][coordinate.getY()];
    }

    public void set(Coordinate coordinate, int value) {
        this.field[coordinate.getX()][coordinate.getY()] = value;
    }

    public int[][] getField() {
        return field;
    }
}
