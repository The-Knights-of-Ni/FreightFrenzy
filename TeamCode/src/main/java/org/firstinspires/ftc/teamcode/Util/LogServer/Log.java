package org.firstinspires.ftc.teamcode.Util.LogServer;

import android.os.Build;

import java.time.LocalDateTime;

public class Log {
    private final String message;
    private final String timestamp;
    private final LogSeverity severity;

    public Log(String message, LogSeverity severity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.timestamp = String.valueOf(LocalDateTime.now());
        }
        else {
            this.timestamp = "";
        }
        this.message = message;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LogSeverity getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return "message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", severity=" + severity;
    }

    public String toJSON() {
        return "{\r\n" + "\"message\": " + this.message + ",\r\n\"timestamp\": " + timestamp + "\r\n\"severity\": " + severity + ",\r\n}";
    }
}
