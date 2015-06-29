package BFIDE;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Created by grabarz_muchomor on 29.06.15.
 */
public class LogConsoleController {
    @FXML
    public TextArea logText;

    public void initialize() {
        logText.setText(String.join("\n",Console.getConsole().getLogs()));
    }
}
