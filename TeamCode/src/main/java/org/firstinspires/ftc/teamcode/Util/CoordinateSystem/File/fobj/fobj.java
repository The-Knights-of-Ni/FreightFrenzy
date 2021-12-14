package org.firstinspires.ftc.teamcode.Util.CoordinateSystem.File.fobj;

import org.firstinspires.ftc.teamcode.Util.CoordinateSystem.Coordinate;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fobj {

  public fobj() {}

  public static Coordinate[] read(String path) throws IOException {
    java.io.File file = new java.io.File(path);
    FileReader fr = new FileReader(file);
    char[] a = new char[50];
    fr.read(a); // reads the content to the array
    StringBuilder output = new StringBuilder();

    for (char c : a) output.append(c); // prints the characters one by one
    fr.close();
    String contains = output.toString();
    String[] splitCoordinate = contains.split("\\|");
    Coordinate[] coordinates = new Coordinate[splitCoordinate.length];
    int now = 0;
    for (String coordinate : splitCoordinate) {
      coordinates[now] =
          new Coordinate(
              Integer.parseInt(coordinate.split(",")[0]),
              Integer.parseInt(coordinate.split(",")[1]));
      now++;
    }
    return coordinates;
  }

  public static void write(Coordinate[] coordinates, String path) throws IOException {
    StringBuilder output = new StringBuilder();
    for (Coordinate coordinate : coordinates) {
      output.append(coordinate.getX() + "," + coordinate.getY() + "|");
    }

    java.io.File file = new java.io.File(path);
    FileWriter fw = new FileWriter(file);
    fw.write(output.toString());
  }
}
