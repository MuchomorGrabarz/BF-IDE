package BFIDE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*MenuBar menuBar = new MenuBar();
        menuBar.useSystemMenuBarProperty().set(true);

        Menu fileMenu = new Menu("File");
        MenuItem open = new MenuItem("Open");
        fileMenu.getItems().add(open);

        Menu helpMenu = new Menu("Help ");
        MenuItem basics = new MenuItem("Brainf**k Basics");
        helpMenu.getItems().add(basics);

        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(helpMenu);

        primaryStage.setScene(new Scene(new Pane(menuBar)));
        primaryStage.show();*/

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Brainf**k IDE");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
