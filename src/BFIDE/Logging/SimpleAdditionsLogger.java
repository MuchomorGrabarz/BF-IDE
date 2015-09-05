package BFIDE.Logging;

public class SimpleAdditionsLogger extends Logger {
    public SimpleAdditionsLogger(LoggerImplementation impl) {
        super(impl);
    }

    @Override
    public void logWarning(String text) {
        this.log(UILoggerMessages.warning + text);
    }

    @Override
    public void logInfo(String text) { this.log(UILoggerMessages.info + text);
    }
}
