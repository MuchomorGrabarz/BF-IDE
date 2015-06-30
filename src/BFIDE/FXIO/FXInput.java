package BFIDE.FXIO;

import BFIDE.IOWrapper.InputWrapper;
import javafx.scene.control.TextArea;

public class FXInput implements InputWrapper {
    TextArea inputArea;

    public FXInput(TextArea inputArea) {
        this.inputArea = inputArea;
    }

    public String getText() {
        return inputArea.getText();
    }
}
