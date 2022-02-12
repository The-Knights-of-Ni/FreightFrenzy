
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

    @Override
    public String toString() {
        StringBuilder formattedLogs = new StringBuilder("{\n");
        for (Log log: logs) {
            formattedLogs.append(log.toJSON());
            formattedLogs.append(",\n");
        }
        formattedLogs.append("}");
        return formattedLogs.toString();
    }
}
