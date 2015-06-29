package BFIDE;

import java.util.ArrayList;

public class MainLogger {
    ArrayList<LoggerImplementation> loggers = new ArrayList<>();

    public MainLogger() {
        loggers.add(new LogAlerts());
    }

    public void subscribeLogger(LoggerImplementation logger) {
        loggers.add(logger);
    }

    public void clear() {
        loggers.clear();
    }

    public void log(String text) {
        for(LoggerImplementation logger : loggers)
            logger.log(text);
    }

}
