package BFIDE;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

import java.util.*;

public class TapeCaretaker extends HBox implements Listener {

    HBox tape;
    Debugger debugger;

    public enum State {DATA, CODE}

    State state;

    public TapeCaretaker(HBox tape, Debugger debugger) {

        this.tape = tape;

        for(int i = 1; i<=50; i++) {
            Label tmp = new Label("0");

            tmp.setStyle("-fx-background-color: white;");

            tmp.setMaxHeight(36);
            tmp.setMinHeight(36);
            tmp.setMinWidth(36);
            tmp.setMaxWidth(36);

            tmp.setAlignment(Pos.CENTER);
            tmp.setTextAlignment(TextAlignment.CENTER);

            this.tape.getChildren().add(tmp);
        }

        this.tape.getChildren().get(0).setStyle("-fx-background-color: lightgreen;");

        this.debugger = debugger;
        this.debugger.registerListener(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void punch() {
        if(state == State.CODE) {
            List<Character> actualCode = debugger.getActualCode();
            int pos = debugger.getActualCodePos();

            for (int i = 0; i < Integer.min(50, actualCode.size() - pos); i++) {
                final int k = i;
                Platform.runLater(() -> ((Label) this.tape.getChildren().get(k)).setText(String.valueOf(actualCode.get(pos + k))));
            }
            for(int i = actualCode.size() - pos; i < 50; i++) {
                final int k = i;
                Platform.runLater(() -> ((Label) this.tape.getChildren().get(k)).setText("0"));
            }
        }
        if(state == State.DATA) {
            List<Integer> actualData = debugger.getActualData();
            int actualDataPos = debugger.getActualDataPos();

            for (int i = 0; i < Integer.min(50, actualData.size() - actualDataPos); i++) {
                final int k = i;
                Platform.runLater(() -> ((Label) this.tape.getChildren().get(k)).setText(String.valueOf(actualData.get(actualDataPos + k))));
            }
            for(int i = actualData.size() - actualDataPos; i < 50; i++) {
                final int k = i;
                Platform.runLater(() -> ((Label) this.tape.getChildren().get(k)).setText("0"));
            }
        }
    }
}
