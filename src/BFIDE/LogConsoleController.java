package BFIDE;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * Created by grabarz_muchomor on 29.06.15.
 */
public class LogConsoleController implements ConsoleListener {
    @FXML
    public TextArea logText;

    @FXML
    public Pane root;

    public void initialize() {
        logText.setText(String.join("\n",Console.getConsole().getLogs()));
        Console.getConsole().registerListener(this);
    }

    @Override
    public void update() {
        logText.setText(String.join("\n", Console.getConsole().getLogs()));
    }
}
