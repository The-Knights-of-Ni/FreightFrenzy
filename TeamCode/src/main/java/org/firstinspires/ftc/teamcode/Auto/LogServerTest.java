package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Util.LogServer.Log;
import org.firstinspires.ftc.teamcode.Util.LogServer.LogManager;
import org.firstinspires.ftc.teamcode.Util.LogServer.LogServerNetworkSocket;
import org.firstinspires.ftc.teamcode.Util.LogServer.LogSeverity;

import java.io.IOException;

@Autonomous(name = "Log Server Test", group = "Concept")
public class LogServerTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        try {
            LogManager logServerTest = new LogManager();
            logServerTest.logger.add(new Log("Test", LogSeverity.INFO));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
