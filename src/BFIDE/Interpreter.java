package BFIDE;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Interpreter {
    private final Integer tapeSize = 30000;
    private char[] tab = new char[tapeSize];
    BFIDE.IO IO;

    public Interpreter(BFIDE.IO stream) {
        IO = stream;
    }

    public void run(List<BFNode> nodes) throws ExecutionException, InterruptedException {
        String input = IO.getText();

        for(int i = 0; i<tapeSize; i++) tab[i] = 0;

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
                    if(inputPos >= inputTab.length) {
                        IO.alert("Insufficient input given");
                        return;
                    }
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

            if(tapePos < 0) {
                IO.alert("Program moved to negative tape indexes");
                return;
            }
            if(tapePos >= tapeSize) {
                IO.alert("Program went over the (" + String.valueOf(tapeSize) + ") tape size limit");
                return;
            }

        }

        IO.alert("Program ended execution");

        IO.setText(output.toString());
    }
}
