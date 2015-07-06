package BFIDE.Logging;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoggerSettingsController {

    private enum ImplState {ALERT,CONSOLE}
    private enum AbstState {NOADD, ADD}

    private static ImplState lastImplState = ImplState.CONSOLE;
    private static AbstState lastAbstState = AbstState.ADD;
    private ImplState currentImplState;
    private AbstState currentAbstState;

    @FXML
    MenuButton loggerImplMenu;
    @FXML
    MenuButton loggerAbstMenu;
    @FXML
    Pane settingLoggerPane;


    public void initialize() {
        if(lastImplState == ImplState.ALERT) {
            loggerImplMenu.setText("Alert");
        } else {
            loggerImplMenu.setText("Console");
        }

        currentImplState = lastImplState;

        if(lastAbstState == AbstState.ADD) {
            loggerAbstMenu.setText("Simple additional text");
        } else {
            loggerAbstMenu.setText("No additional text");
        }

        currentAbstState = lastAbstState;
    }

    public void setAlerts() {
        currentImplState = ImplState.ALERT;
        loggerImplMenu.setText("Alerts");
    }
    public void setConsole() {
        currentImplState = ImplState.CONSOLE;
        loggerImplMenu.setText("Console");
    }

    public void setNoAdditional() {
        currentAbstState = AbstState.NOADD;
        loggerAbstMenu.setText("No additional text");
    }
    public void setSimpleAdditional() {
        currentAbstState = AbstState.ADD;
        loggerAbstMenu.setText("Simple additional text");
    }

    public void cancel() {
        ((Stage) settingLoggerPane.getScene().getWindow()).close();
    }
    public void ok() {

        LoggerImplementation impl;

        if(currentImplState == ImplState.CONSOLE) {
            impl = new LoggerConsoleImplementation();
        } else {
            impl = new LoggerAlertImplementation();
        }

        if(currentAbstState == AbstState.ADD) {
            Logger.setLogger(new SimpleAdditionsLogger(impl));
        } else {
            Logger.setLogger(new NoAdditionsLogger(impl));
        }

        lastImplState = currentImplState;
        lastAbstState = currentAbstState;

        ((Stage) settingLoggerPane.getScene().getWindow()).close();
    }
}
