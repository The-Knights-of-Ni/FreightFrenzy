package org.firstinspires.ftc.teamcode.Util.LogServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LogSync {
    public Logger logger = new Logger(9119);

    public LogSync() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
    }
}