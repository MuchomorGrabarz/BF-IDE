package BFIDE;

import javafx.scene.control.TextArea;

public class FXOutput implements OutputWrapper {
    TextArea outputArea;

    public FXOutput(TextArea outputArea) {
        this.outputArea = outputArea;
    }

    public void setText(String text) {
        outputArea.setText(text);
    }
}
