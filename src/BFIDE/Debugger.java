package BFIDE;

import javafx.application.Platform;
import javafx.scene.control.TextInputControl;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Debugger {
    private final Integer tapeSize = 30000;
    private char[] tab = new char[tapeSize];
    TextInputControl inputControl;
    TextInputControl outputControl;

    List<DebuggerListener> listeners;

    List<BFNode> nodes;

    char[] inputTab;

    int dataTapePos, codeTapePos, inputPos;

    public Debugger(TextInputControl inputControl, TextInputControl outputControl) {
        this.inputControl = inputControl;
        this.outputControl = outputControl;

        listeners = new LinkedList<>();
    }

    Set<Integer> breakpoints;

    public void prepare(List<BFNode> nodes) {
        dataTapePos = 0;
        codeTapePos = 0;
        inputPos = 0;

        this.nodes = nodes;

        FutureTask<String> stringTask = new FutureTask<>(() -> inputControl.getText());
        Platform.runLater(stringTask);

        try {
            inputTab = stringTask.get().toCharArray();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(int i = 0; i<tapeSize; i++) tab[i] = 0;
        breakpoints = new HashSet<>();

        for(DebuggerListener l : listeners) l.kick();
    }
    public void singleStep() {
        StringBuilder output = new StringBuilder();

        FutureTask<String> stringTask = new FutureTask<String>(() -> outputControl.getText());
        Platform.runLater(stringTask);
        try {
            output.append(stringTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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

        Platform.runLater(() -> outputControl.setText(String.valueOf(output)));

        for(DebuggerListener l : listeners) l.kick();
    }
    public void run() {
        StringBuilder output = new StringBuilder();

        FutureTask<String> stringTask = new FutureTask<String>(() -> outputControl.getText());
        Platform.runLater(stringTask);
        try {
            output.append(stringTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        while(codeTapePos < nodes.size() && !breakpoints.contains(codeTapePos)) {
            switch (nodes.get(codeTapePos).getType()) {
                case '>':
                    codeTapePos++;
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
        }

        Platform.runLater(() -> outputControl.setText(String.valueOf(output)));
        for(DebuggerListener l : listeners) l.kick();
    }

    public void addBreakpoint(int pos) {
        breakpoints.add(pos);
    }
    public void deleteBreakpoint(int pos) {
        breakpoints.remove(pos);
    }

    //things for listeners

    public void registerListener(DebuggerListener listener) {
        listeners.add(listener);
    }

    public List<Character> getActualCode() {
        ArrayList<Character> result = new ArrayList<>();

        for(BFNode n : nodes) {
            result.add(n.getType());
        }

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
