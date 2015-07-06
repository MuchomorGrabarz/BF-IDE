package BFIDE.Logging;

public class NoAdditionsLogger extends Logger {
    public NoAdditionsLogger(LoggerImplementation impl) {
        super(impl);
    }

    @Override
    public void logWarning(String text) {
        this.log(text);
    }

    @Override
    public void logInfo(String text) {
        this.log(text);
    }
}
