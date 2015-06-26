package BFIDE;

import java.util.*;
public class Interpreter {
    String input;
    String code;

    Parser myParser;

    private char[] tab = new char[30000];

    public void setCode(String code) {
        this.code = code;
    }
    public void setInput(String input) {
        this.input = input;
    }
    public void setParser(Parser p) {
        myParser = p;
    }

    public String run() {
        if(input == null) throw new RuntimeException("Input needed");
        if(code == null) throw new RuntimeException("Code needed");
        if(myParser == null) throw new RuntimeException("Parser needed");

        List<BFNode> nodes = myParser.parse(code);
        for(int i = 0; i<30000; i++) tab[i] = 0;

        int codePos = 0, inputPos = 0, tapePos = 10000;

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

        return output.toString();
    }
}
