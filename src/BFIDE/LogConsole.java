package BFIDE;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class LogConsole implements LoggerImplementation {

    @FXML
    TextArea logText;

    static LogConsole me;
    static Stage myStage;

    public LogConsole() {
        me = this;
    }

    public void init(Stage stage) {
        logText.setText("Console was started");
        myStage = stage;
    }

    public void log(String text) {
        logText.appendText(text);
    }

    public void close() {
        me.close();
    }


}
