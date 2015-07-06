package BFIDE.Logging;

public abstract class Logger {

    static private Logger currentLogger;
    private LoggerImplementation impl;

    static {
        currentLogger = new SimpleAdditionsLogger(new LoggerConsoleImplementation());
    }


    static public Logger getLogger() {
        return currentLogger;
    }
    static public void setLogger(Logger newLogger) {
         currentLogger = newLogger;
    }

    public Logger(LoggerImplementation impl) {
        this.impl = impl;
    }

    protected void log(String text) {
       impl.log(text);
    }

    public abstract void logWarning(String text);
    public abstract void logInfo(String text);

}
