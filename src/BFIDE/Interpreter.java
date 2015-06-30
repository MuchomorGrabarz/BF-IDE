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

        int codeTapePos = 0, inputPos = 0, dataTapePos = 0;

        char[] inputTab = input.toCharArray();
        StringBuilder output = new StringBuilder();

        while(codeTapePos < nodes.size()) {
            switch (nodes.get(codeTapePos).getType()) {
                case '>':
                    dataTapePos++;
                    break;
                case '<':
                    dataTapePos--;
                    break;
                case '+':
                    tab[dataTapePos] = (char) (((int) tab[dataTapePos] + 1)%256);
                    break;
                case '-':
                    tab[dataTapePos] = (char) (((int) tab[dataTapePos] + 255)%256);
                    break;
                case ',':
                    if(inputPos >= inputTab.length) {
                        logger.alert(UIMessages.noInput);
                        return;
                    }
                    tab[dataTapePos] = inputTab[inputPos++];
                    break;
                case '.':
                    output.append(tab[dataTapePos]);
                    break;
                case '[':
                    if(tab[dataTapePos] == 0) codeTapePos = nodes.get(codeTapePos).getJump();
                    break;
                case ']':
                    codeTapePos = nodes.get(codeTapePos).getJump()-1;
                    break;
            }
            codeTapePos++;

            if(dataTapePos < 0) {
                logger.alert(UIMessages.negIndexes);
                return;
            }

            if(dataTapePos >= tapeSize) {
                logger.alert(UIMessages.outOfTape);
                return;
            }

        }

        logger.alert(UIMessages.programEnded);

        out.setText(output.toString());
    }
}
