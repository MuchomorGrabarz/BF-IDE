package BFIDE.FXIO;

import BFIDE.IOWrapper.InputWrapper;
import BFIDE.UIMessages;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FXInput implements InputWrapper {
    TextArea inputArea;

    public FXInput(TextArea inputArea) {
        this.inputArea = inputArea;
    }

    public String getText() {
        String result = UIMessages.inputException;
        FutureTask<String> stringTask = new FutureTask<>(inputArea::getText);
        Platform.runLater(stringTask);
        try {
            result = stringTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
