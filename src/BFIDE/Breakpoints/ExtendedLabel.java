package BFIDE.Breakpoints;

import BFIDE.Tape.BFNode;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ExtendedLabel extends Label {
    BFNode node;
    boolean marked = false;

    public ExtendedLabel(String text, BFNode node) {
        super(text);

        this.setStyle("-fx-background-color: white");

        this.node = node;
        this.setOnMouseClicked(this::changeMarked);
    }

    private void changeMarked(MouseEvent e) {
        if(marked) {
            this.setStyle("-fx-background-color: white");
            this.marked = false;
            this.node.setBreakpoint(false);
        } else {
            this.setStyle("-fx-background-color: red");
            this.marked = true;
            this.node.setBreakpoint(true);
        }
    }
}
