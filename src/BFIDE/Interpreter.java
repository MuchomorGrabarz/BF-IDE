package BFIDE;

import javafx.application.Platform;
import javafx.scene.control.TextInputControl;

import java.util.List;
public class Interpreter {
    private char[] tab = new char[30000];
    TextInputControl inputControl;
    TextInputControl outputControl;

    public Interpreter(TextInputControl inputControl, TextInputControl outputControl) {
        this.inputControl = inputControl;
        this.outputControl = outputControl;
    }

    public void run(List<BFNode> nodes) {
        String input = inputControl.getText();

        for(int i = 0; i<30000; i++) tab[i] = 0;

        int codePos = 0, inputPos = 0, tapePos = 0;

        char[] inputTab = input.toCharArray();
        StringBuilder output = new StringBuilder();

        while(codePos < nodes.size()) {
            switch (nodes.get(codePos).getType()) {
                case '>':
                    tapePos++;
                    break;
                case '<':
                    tapePos--;
                    break;
                case '+':
                    tab[tapePos] = (char) (((int) tab[tapePos] + 1)%256);
                    break;
                case '-':
                    tab[tapePos] = (char) (((int) tab[tapePos] + 255)%256);
                    break;
                case ',':
                    tab[tapePos] = inputTab[inputPos++];
                    break;
                case '.':
                    output.append(tab[tapePos]);
                    break;
                case '[':
                    if(tab[tapePos] == 0) codePos = nodes.get(codePos).getJump();
                    break;
                case ']':
                    codePos = nodes.get(codePos).getJump()-1;
                    break;
            }
            codePos++;
        }

        Platform.runLater(() -> outputControl.setText(output.toString()));
    }
}
