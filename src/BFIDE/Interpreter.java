package BFIDE;

import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Interpreter {
    public final Integer tapeSize = 30000;
    private char[] tab = new char[tapeSize];
    InputWrapper in;
    OutputWrapper out;
    LoggerWrapper logger;

    public Interpreter(InputWrapper in, OutputWrapper out, LoggerWrapper logger) {
        this.in = in;
        this.out = out;
        this.logger = logger;
    }

    public void run(List<BFNode> nodes) throws ExecutionException, InterruptedException {
        String input = in.getText();

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
                        logger.alert("Insufficient input given");
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
                logger.alert("Program moved to negative tape indexes");
                return;
            }
            if(tapePos >= tapeSize) {
                logger.alert("Program went over the (" + String.valueOf(tapeSize) + ") tape size limit");
                return;
            }

        }

        logger.alert("Program ended execution");

        out.setText(output.toString());
    }
}
