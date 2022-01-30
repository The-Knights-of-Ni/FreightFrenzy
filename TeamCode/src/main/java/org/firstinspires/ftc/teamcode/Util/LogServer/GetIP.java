package org.firstinspires.ftc.teamcode.Util.LogServer;

import java.net.*;

public class GetIP {
    private final int port = 9191;
    public GetIP() {
        int nreq = 1;
        try
        {
            ServerSocket sock = new ServerSocket(port);
            for (;;)
            {
                Socket newsock = sock.accept();
            }
        }
        catch (Exception ignored)
        {
        }
    }
}
