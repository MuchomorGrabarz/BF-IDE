package BFIDE.FXIO;

import BFIDE.IOWrapper.InputWrapper;
import BFIDE.UIMessages;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FXInput implements InputWrapper {
    TextArea inputArea;
    String characters;
    int pos;

    public FXInput(TextArea inputArea) {
        this.inputArea = inputArea;

        characters = inputArea.getText();
        pos=0;
    }

    @Override
    public void reset() {
        characters = inputArea.getText();
        pos = 0;
    }

    @Override
    public boolean hasNext() {
        if(pos >= characters.length()) return false;
        return true;
    }

    @Override
    public char getChar() {
        char result = characters.charAt(pos);
        pos++;
        return result;
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
