package org.firstinspires.ftc.teamcode.Util.LogServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LogServerNetworkSocket extends Thread {
    public Logger logger;
    private final int port = 9119;
    ServerSocket serverSocket;
    public LogServerNetworkSocket() throws IOException {
        logger = new Logger();
        serverSocket = new ServerSocket(port);
    }

    public void start() {
        while (true) {

            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                BufferedReader in = null;
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );

                // Get output buffer
                OutputStreamWriter writer = null;
                writer = new OutputStreamWriter(clientSocket.getOutputStream());
                writer.write(logger.toString());
                System.out.println("Wrote out all, reading");
                String s;
                while ((s = in.readLine()) != null) {
                    System.out.println(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}