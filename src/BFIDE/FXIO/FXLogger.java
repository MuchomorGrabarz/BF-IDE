package BFIDE.FXIO;

import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.Logging.Logger;

public class FXLogger implements LoggerWrapper {
    public void warningAlert(String text) {
        Logger.getLogger().logWarning(text);
    }
    public void infoAlert(String text) {
        Logger.getLogger().logInfo(text);
    }
}
