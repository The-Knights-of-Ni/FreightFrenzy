package org.firstinspires.ftc.teamcode.Util.FileParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class File {
    public File() {

    }

    public abstract void load(String path) throws IOException;

  public String open(String path) throws IOException {
        java.io.File file = new java.io.File(path);
        FileReader fr = new FileReader(file);
        char[] a = new char[50];
        fr.read(a); // reads the content to the array
        StringBuilder output = new StringBuilder();

        for (char c : a) output.append(c); // prints the characters one by one
        fr.close();
        return output.toString();
    }
}
