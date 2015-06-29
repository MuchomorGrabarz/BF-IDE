package BFIDE;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by grabarz_muchomor on 29.06.15.
 */
public class LoggerSetingsController {

    private enum State {ALERT,CONSOLE};

    private static State lastState = State.CONSOLE;
    private State currentState;

    @FXML
    MenuButton loggerImplMenu;

    @FXML
    Pane settingLoggerPane;

    public void initialize() {
        currentState = lastState;
    }

    public void setAlerts(ActionEvent actionEvent) {
        currentState = State.ALERT;
        loggerImplMenu.setText("Alerts");
    }
    public void setConsole(ActionEvent actionEvent) {
        currentState = State.CONSOLE;
        loggerImplMenu.setText("Console");
    }

    public void cancel(ActionEvent actionEvent) {
        ((Stage) settingLoggerPane.getScene().getWindow()).close();
    }
    public void ok(ActionEvent actionEvent) {
        if(currentState == State.CONSOLE) {
            MainLogger.setLogger(new MainLogger(new LoggerConsoleImplementation()));
        } else {
            MainLogger.setLogger(new MainLogger(new LoggerAlertImplementation()));
        }

        lastState = currentState;
        ((Stage) settingLoggerPane.getScene().getWindow()).close();
    }
}
