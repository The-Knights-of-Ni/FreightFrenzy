package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Util.LogServer.Log;
import org.firstinspires.ftc.teamcode.Util.LogServer.LogServerNetworkSocket;

import java.io.IOException;

@Autonomous(name = "Log Server Test", group = "Concept")
public class LogServerTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        try {
            LogServerNetworkSocket logServerNetworkSocket = new LogServerNetworkSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
