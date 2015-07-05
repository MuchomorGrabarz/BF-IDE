package BFIDE;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class TapeCaretaker extends HBox implements Listener {

    HBox tapeView;
    Tape tape;

    public enum State {DATA, CODE}

    State state;

    public TapeCaretaker(HBox tapeView, Tape tape) {

        this.tapeView = tapeView;

        for(int i = 1; i<=50; i++) {
            Label tmp = new Label("0");

            tmp.setStyle("-fx-background-color: white;");

            tmp.setMaxHeight(36);
            tmp.setMinHeight(36);
            tmp.setMinWidth(36);
            tmp.setMaxWidth(36);

            tmp.setAlignment(Pos.CENTER);
            tmp.setTextAlignment(TextAlignment.CENTER);

            this.tapeView.getChildren().add(tmp);
        }

        this.tapeView.getChildren().get(0).setStyle("-fx-background-color: lightgreen;");

        this.tape = tape;
        this.tape.registerListener(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void punch() {
        List<? extends TypeBox> list = tape.getPiece(tape.getPosition(),50);
        for(int i = 0; i<50; i++) {
            final int finalI = i;
            Platform.runLater(() -> ((Label) tapeView.getChildren().get(finalI)).setText(String.valueOf(list.get(finalI).getType())));
        }
    }
}
