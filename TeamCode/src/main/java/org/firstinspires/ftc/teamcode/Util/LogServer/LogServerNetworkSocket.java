package org.firstinspires.ftc.teamcode.Util.LogServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LogServerNetworkSocket {
    public Logger logger;
    private final int port = 9119;
    public void LogServerNetworkSocket() throws IOException {
        logger = new Logger();
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {

            Socket clientSocket = serverSocket.accept();


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            // Get output buffer
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()));
            // Write output
            writer.write("Hello");
            writer.flush();

            System.out.println("Wrote out all, reading");
            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
        }

    }
}