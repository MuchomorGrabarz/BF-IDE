package BFIDE.Logging;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class LoggerAlertImplementation implements LoggerImplementation {
    public void log(String text) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Some information");
            alert.setHeaderText(null);
            alert.setContentText(text);
            alert.showAndWait();
        });
    }
}
