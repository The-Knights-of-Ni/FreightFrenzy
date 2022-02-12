package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Util.LogServer.Log;

import java.io.IOException;

@Autonomous(name = "Log Server Test", group = "Concept")
public class LogServerTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        LogSync logSync = null;
        try {
            logSync = new LogSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert logSync != null;
        logSync.logger.add(new Log());
    }
}
