package org.firstinspires.ftc.teamcode.Util.LogServer;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LogSync {
    public LogSync() {
        try {
            URI uri = new URI("http://java.sun.com/");
            URL url = uri.toURL();
            InputStream in = url.openStream();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}