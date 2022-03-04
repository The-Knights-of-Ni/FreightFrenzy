package org.firstinspires.ftc.teamcode.Util.LogServer;

import java.io.IOException;

public class LogManager {
    private LogServerNetworkSocket logServerNetworkSocket;
    public Logger logger;
    public LogManager() throws IOException {
        logServerNetworkSocket = new LogServerNetworkSocket();
        logServerNetworkSocket.start();
        logger = logServerNetworkSocket.logger;
    }
}
