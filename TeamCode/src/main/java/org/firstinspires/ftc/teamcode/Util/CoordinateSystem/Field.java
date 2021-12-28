package org.firstinspires.ftc.teamcode.Util.CoordinateSystem;

import java.util.ArrayList;

/**
 * This is the Playing Field
 * The objects are baked into the field for speed
 */
public class Field {
    public final int length = 100; // length
    public final int width = 100; // width
    ArrayList<Object2> object2s = new ArrayList<>(); // List of objects
    private final int[][] field = new int[length][width]; // Objects are baked here

    public Field() {
        int x;
        int y;
        for (x = 0; x < length; x++) {
            for (y = 0; y < width; y++) {
                this.field[x][y] = 0;
            }
        }
    }

    public Field(ArrayList<Object2> object2s) {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                this.field[x][y] = 0;
            }
        }
        this.object2s = object2s;
        for (Object2 object2 : object2s) {
            for (Coordinate coordinate : object2.getOccupies()) {
                this.field[coordinate.getX()][coordinate.getY()] = 1;
            }
        }
    }

    public boolean isBlocked(Coordinate goTo) {
        return field[goTo.getX()][goTo.getY()] == -1;
    }

    public void addObject(Object2 object2) {
        for (Coordinate coordinate : object2.getOccupies()) {
            this.field[coordinate.getX()][coordinate.getY()] = 1;
        }
        object2s.add(object2);
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
