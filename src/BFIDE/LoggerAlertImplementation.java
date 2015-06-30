package BFIDE;

import javafx.scene.control.Alert;

public class LoggerAlertImplementation implements LoggerImplementation {
    public void log(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Some information");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
