package BFIDE.Tape;

import BFIDE.Listener;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class TapeCaretaker extends HBox implements Listener {

    HBox tapeView;
    Tape tape;

    class TapeCell extends Label {

        private boolean first;
        private boolean breakpoint;

        public TapeCell() {
            super("0");
            this.setMaxHeight(36);
            this.setMinHeight(36);
            this.setMinWidth(36);
            this.setMaxWidth(36);

            this.setAlignment(Pos.CENTER);
            this.setTextAlignment(TextAlignment.CENTER);

            this.first = false;
            this.breakpoint = false;

            solveColor();
        }

        public void setFirst(boolean first) {
            this.first = first;
            solveColor();
        }
        public void setBreakpoint(boolean breakpoint) {
            this.breakpoint = breakpoint;
            solveColor();
        }

        private void solveColor() {
            if(first && breakpoint) {
                this.setStyle("-fx-background-color: purple;");
            } else if(first && !breakpoint) {
                this.setStyle("-fx-background-color: blue;");
            } else if(!first && breakpoint) {
                this.setStyle("-fx-background-color: red;");
            } else {
                this.setStyle("-fx-background-color: white;");
            }
        }
    }

    public TapeCaretaker(HBox tapeView, Tape tape) {

        this.tapeView = tapeView;

        for(int i = 1; i<=50; i++) {
            TapeCell tmp = new TapeCell();
            this.tapeView.getChildren().add(tmp);
        }
        ((TapeCell) this.tapeView.getChildren().get(0)).setFirst(true);

        this.tape = tape;
        this.tape.registerListener(this);
    }

    @Override
    public void punch() {
        List<BFNode> list = tape.getPiece(tape.getPosition(),50);

        Platform.runLater(() -> {
            for(int i = 0; i<50; i++) {
                ((Label) tapeView.getChildren().get(i)).setText(String.valueOf(list.get(i).getType()));
                ((TapeCell) tapeView.getChildren().get(i)).setBreakpoint(list.get(i).isBreakpoint());
            }
        });

    }
}
