package BFIDE;

import javafx.application.Platform;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FXIO implements IO {
    public String getText() throws ExecutionException, InterruptedException {
        FutureTask<String> gibInput = new FutureTask<String>(() -> Controller.me.inputArea.getText());
        Platform.runLater(gibInput);
        String input = gibInput.get();
        return Controller.me.inputArea.getText();
    }

    public void setText(String text) {
        Platform.runLater(() -> Controller.me.outputArea.setText(text));
    }

    public void alert(String text) { Platform.runLater(() -> Controller.me.logger.log(text)); }
}
