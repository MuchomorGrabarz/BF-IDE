package BFIDE;

public class FXIO implements BiDirStream {
    public String getText() {
        return Controller.me.inputArea.getText();
    }

    public void setText(String text) {
        Controller.me.outputArea.setText(text);
    }
}
