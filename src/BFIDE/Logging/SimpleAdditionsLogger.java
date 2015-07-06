package BFIDE.Logging;

public class SimpleAdditionsLogger extends Logger {
    public SimpleAdditionsLogger(LoggerImplementation impl) {
        super(impl);
    }

    @Override
    public void logWarning(String text) {
        this.log("Warning:\n" + text);
    }

    @Override
    public void logInfo(String text) {
        this.log("Info:\n" + text);
    }
}
