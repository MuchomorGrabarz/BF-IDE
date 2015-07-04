package BFIDE.FXIO;

import BFIDE.IOWrapper.OutputWrapper;
import javafx.scene.control.TextArea;

public class FXOutput implements OutputWrapper {
    TextArea outputArea;
    StringBuilder currentOutput;

    public FXOutput(TextArea outputArea) {
        this.outputArea = outputArea;
        currentOutput = new StringBuilder();
    }

    @Override
    public void reset() {
        currentOutput.delete(0,currentOutput.length()-1);
        outputArea.setText(currentOutput.toString());
    }
    @Override
    public void putChar(Character c) {
        currentOutput.append(c);
        outputArea.setText(currentOutput.toString());
    }

    public void setText(String text) {
        outputArea.setText(text);
    }
}
