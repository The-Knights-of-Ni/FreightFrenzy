package org.firstinspires.ftc.teamcode.Util.CoordinateSystem.File.fobj;

import org.firstinspires.ftc.teamcode.Util.CoordinateSystem.Coordinate;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class fobj {
    public static final String version = "0.0.0";

    public fobj() {
    }

    public static ArrayList<Coordinate> read(String path) throws IOException {
        java.io.File file = new java.io.File(path);
        FileReader fr = new FileReader(file);
        char[] a = new char[50];
        fr.read(a); // reads the content to the array
        StringBuilder output = new StringBuilder();

        for (char c : a) output.append(c); // prints the characters one by one
        fr.close();
        String[] contains = output.toString().split("&");
        String[] splitCoordinate = contains[1].split("\\|");
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int now = 0;
        for (String coordinate : splitCoordinate) {
            coordinates.set(
                    now,
                    new Coordinate(
                            Integer.parseInt(coordinate.split(",")[0]),
                            Integer.parseInt(coordinate.split(",")[1])));
            now++;
        }
        return coordinates;
    }

    public static void write(ArrayList<Coordinate> coordinates, String path, int maxX, int maxY)
            throws IOException {
        StringBuilder output = new StringBuilder();
        output.append(maxX).append(",").append(maxY).append("&");
        for (Coordinate coordinate : coordinates) {
            output.append(coordinate.getX()).append(",").append(coordinate.getY()).append("|");
        }

        java.io.File file = new java.io.File(path);
        FileWriter fw = new FileWriter(file);
        fw.write(output.toString());
    }
}
