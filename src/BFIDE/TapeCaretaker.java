package BFIDE;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

/**
 * Created by michaziobro on 26.06.2015.
 */
public class TapeCaretaker extends HBox {

    HBox tape;

    public TapeCaretaker(HBox tape) {

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

    }
}
