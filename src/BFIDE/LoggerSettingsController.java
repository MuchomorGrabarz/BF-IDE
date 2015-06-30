package BFIDE;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoggerSettingsController {

    private enum State {ALERT,CONSOLE}

    private static State lastState = State.CONSOLE;
    private State currentState;

    @FXML
    MenuButton loggerImplMenu;

    @FXML
    Pane settingLoggerPane;

    public void initialize() {
        currentState = lastState;
    }

    public void setAlerts() {
        currentState = State.ALERT;
        loggerImplMenu.setText("Alerts");
    }
    public void setConsole() {
        currentState = State.CONSOLE;
        loggerImplMenu.setText("Console");
    }

    public void cancel() {
        ((Stage) settingLoggerPane.getScene().getWindow()).close();
    }
    public void ok() {
        if(currentState == State.CONSOLE) {
            MainLogger.setLogger(new MainLogger(new LoggerConsoleImplementation()));
        } else {
            MainLogger.setLogger(new MainLogger(new LoggerAlertImplementation()));
        }

        lastState = currentState;
        ((Stage) settingLoggerPane.getScene().getWindow()).close();
    }
}
