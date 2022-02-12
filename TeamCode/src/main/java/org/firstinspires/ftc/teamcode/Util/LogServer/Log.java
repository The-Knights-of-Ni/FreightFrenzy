
import java.time.LocalDateTime;

public class Log {
    private final String message;
    private final String timestamp;
    private final LogSeverity severity;

    public Log(String message, LogSeverity severity) {
        this.timestamp = String.valueOf(LocalDateTime.now());
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
        return "{\n" + "\"message\": " + this.message + ",\n\"timestamp\": " + timestamp + "\n\"severity\": " + severity + ",\n}";
    }
}
