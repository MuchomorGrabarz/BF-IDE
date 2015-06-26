package BFIDE;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class Controller {
    @FXML
    MenuItem openFile;
    @FXML
    MenuItem saveFile;
    @FXML
    MenuItem saveFileAs;
    @FXML
    MenuItem closeFile;

    @FXML
    TextArea codeArea;

    File currentFile = null;

    public void openFileAction() throws IOException {
        Stage fileStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        File selectedFile = fileChooser.showOpenDialog(fileStage);

        String buffer = "";
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)));

        final StringBuilder result = new StringBuilder();
        input.lines().forEach(x -> result.append(x + "\n"));
        input.close();

        final String finalResult = String.valueOf(result);

        Platform.runLater(() -> codeArea.setText(finalResult));
    }
    public void saveFileAction() throws IOException {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentFile)));
        output.write(codeArea.getText());
        output.close();
    }
    public void saveFileAsAction() throws IOException {
        Stage fileStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        File selectedFile = fileChooser.showSaveDialog(fileStage);

        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentFile)));
        output.write(codeArea.getText());
        output.close();
    }
    public void closeAction() {
        System.exit(0);
    }
}
