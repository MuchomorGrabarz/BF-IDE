package BFIDE.Logging;

import BFIDE.Listener;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class LogConsoleController implements Listener {
    @FXML
    public TextArea logText;

    @FXML
    public Pane root;

    public void initialize() {
        logText.setText(String.join("\n", Console.getConsole().getLogs()));
        Console.getConsole().registerListener(this);
    }

    @Override
    public void punch() {
        logText.setText(String.join("\n", Console.getConsole().getLogs()));
    }
}
