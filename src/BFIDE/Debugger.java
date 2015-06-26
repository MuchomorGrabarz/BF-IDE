package BFIDE;

import javafx.scene.control.TextInputControl;

import java.util.List;

/**
 * Created by michaziobro on 26.06.2015.
 */
public class Debugger {
    private char[] tab = new char[30000];
    TextInputControl inputControl;
    TextInputControl outputControl;

    public Debugger(TextInputControl inputControl, TextInputControl outputControl) {
        this.inputControl = inputControl;
        this.outputControl = outputControl;
    }

    public void prepare(List<BFNode> nodes) {

    }

    public void singleStep() {

    }
    public void run() {

    }
}
