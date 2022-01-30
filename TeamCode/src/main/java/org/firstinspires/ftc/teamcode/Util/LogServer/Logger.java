package org.firstinspires.ftc.teamcode.Util.LogServer;

import java.util.ArrayList;


public class Logger {
    private ArrayList<Log> logs = new ArrayList<>();

    public Logger() {

    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void add(Log log) {
        logs.add(log);
    }
}
