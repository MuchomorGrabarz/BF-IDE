package BFIDE;

public class FXLogger implements LoggerWrapper {
    MainLogger logger;

    public FXLogger(MainLogger logger) {
        this.logger = logger;
    }

    public void alert(String text) {
        logger.log(text);
    }
}
