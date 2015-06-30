package BFIDE;

public class MainLogger {

    static private MainLogger currentLogger;
    private LoggerImplementation impl;

    static {
        currentLogger = new MainLogger(new LoggerConsoleImplementation());
    }


    static public MainLogger getLogger() {
        return currentLogger;
    }
    static public void setLogger(MainLogger newLogger) {
         currentLogger = newLogger;
    }

    public MainLogger(LoggerImplementation impl) {
        this.impl = impl;
    }

    public void log(String text) {
       impl.log(text);
    }

}
