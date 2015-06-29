package BFIDE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Brainf**k IDE");
        primaryStage.setScene(new Scene(root, 1000, 600));

        primaryStage.show();

        Controller.me.init();
    }

    public LogConsole startLogConsole() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("logConsole.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Log console");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        LogConsole.me.init(stage);

        return LogConsole.me;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
