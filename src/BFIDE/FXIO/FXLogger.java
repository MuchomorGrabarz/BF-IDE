package BFIDE.FXIO;

import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.Logging.MainLogger;

public class FXLogger implements LoggerWrapper {
    public void alert(String text) {
        MainLogger.getLogger().log(text);
    }
}
