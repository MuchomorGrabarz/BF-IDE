package BFIDE.HeartOfEverything;

import BFIDE.BFNode;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Listener;
import BFIDE.UIMessages;

import java.util.*;

public class Debugger implements Executor {
    private final Integer tapeSize = 30000;
    private char[] tab = new char[tapeSize];
    InputWrapper in;
    OutputWrapper out;
    LoggerWrapper logger;
    StringBuilder output;

    List<Listener> listeners;

    List<BFNode> nodes;

    char[] inputTab;

    int dataTapePos, codeTapePos, inputPos;

    public Debugger(InputWrapper in, OutputWrapper out, LoggerWrapper logger) {
        this.in = in;
        this.out = out;
        this.logger = logger;

        listeners = new LinkedList<>();
    }

    Set<Integer> breakpoints;

    public Integer tapeSize() {
        return tapeSize;
    }

    public void prepare(List<BFNode> nodes) {
        dataTapePos = 0;
        codeTapePos = 0;
        inputPos = 0;

        this.nodes = nodes;

        output = new StringBuilder();

        out.setText(String.valueOf(output));

        inputTab = in.getText().toCharArray();

        for(int i = 0; i<tapeSize; i++) tab[i] = 0;
        breakpoints = new HashSet<>();

        listeners.forEach(Listener::punch);
    }

    public void singleStep() {

        if(codeTapePos >= nodes.size()) {
            logger.alert(UIMessages.programEnded);
            return;
        }

        if(dataTapePos < 0) {
            logger.alert(UIMessages.illegaState);
            return;
        }

        if(dataTapePos >= tapeSize) {
            logger.alert(UIMessages.illegaState);
            return;
        }

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

        if(codeTapePos == nodes.size())
            logger.alert(UIMessages.programEnded);

        out.setText(String.valueOf(output));

        listeners.forEach(BFIDE.Listener::punch);
    }
    public void run() {
        StringBuilder output = new StringBuilder();

        if(codeTapePos == nodes.size()) {
            logger.alert(UIMessages.alreadyEnded);
            return;
        }

        while(codeTapePos < nodes.size() && !breakpoints.contains(codeTapePos)) {
            switch (nodes.get(codeTapePos).getType()) {
                case '>':
                    dataTapePos++;
                    break;
                case '<':
                    dataTapePos--;
                    break;
                case '+':
                    tab[dataTapePos] = (char) (((int) tab[dataTapePos] + 1) % 256);
                    break;
                case '-':
                    tab[dataTapePos] = (char) (((int) tab[dataTapePos] + 255) % 256);
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
                    if (tab[dataTapePos] == 0) codeTapePos = nodes.get(codeTapePos).getJump();
                    break;
                case ']':
                    codeTapePos = nodes.get(codeTapePos).getJump() - 1;
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

        out.setText(String.valueOf(output));
        listeners.forEach(BFIDE.Listener::punch);
    }

    public void addBreakpoint(int pos) {
        breakpoints.add(pos);
    }
    public void deleteBreakpoint(int pos) {
        breakpoints.remove(pos);
    }

    //things for listeners

    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    public List<Character> getActualCode() {
        ArrayList<Character> result = new ArrayList<>();
        nodes.forEach(x -> result.add(x.getType()));

        return result;
    }
    public int getActualCodePos() {
        return codeTapePos;
    }

    public List<Integer> getActualData() {
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i<=100; i++) {
            result.add((int) tab[i]);
        }

        return result;
    }
    public int getActualDataPos() {
        return dataTapePos;
    }
}
