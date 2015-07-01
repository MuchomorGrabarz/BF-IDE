package BFIDE;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ParserSettingController {
    @FXML
    CheckBox cLike;
    @FXML
    CheckBox shellLike;

    @FXML
    Pane settingParserPane;

    private Consumer<Parser> lastAction = null;

    void setLastAction(Consumer<Parser> lastAction) {
        this.lastAction = lastAction;
    }

    public void ok() {
        Parser newParser = new SimpleParser();

        if(cLike.isSelected()) newParser = new ParserIgnoringCShortComments(newParser);
        if(shellLike.isSelected()) newParser = new ParserIgnoringShellComments(newParser);

        lastAction.accept(newParser);

        ((Stage) settingParserPane.getScene().getWindow()).close();
    }
    public void cancel() {
        ((Stage) settingParserPane.getScene().getWindow()).close();
    }
}