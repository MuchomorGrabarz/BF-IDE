package BFIDE;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class Controller {
    @FXML
    TextArea codeArea;
    @FXML
    TextArea inputArea;
    @FXML
    TextArea outputArea;

    @FXML
    Pane tapePane;
    @FXML
    MenuButton modeMenu;

    @FXML
    Button runButton;

    File currentFile = null;

    CodePreparer codePreparer;
    Debugger debugger;
    Interpreter interpreter;

    private enum State {DEBUGGER, INTERPRETER};
    State state;

    public Controller() {
        state = State.DEBUGGER;

        codePreparer = new CodePreparer(codeArea);
        codePreparer.setParser(new SimpleParser());
        debugger = new Debugger(inputArea, outputArea);
        interpreter = new Interpreter(inputArea, outputArea);
    }

    public void openFileAction() throws IOException {
        Stage fileStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        File selectedFile = fileChooser.showOpenDialog(fileStage);

        if(selectedFile == null) return;

        String buffer = "";
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)));

        final StringBuilder result = new StringBuilder();
        input.lines().forEach(x -> result.append(x + "\n"));
        input.close();

        final String finalResult = String.valueOf(result);

        Platform.runLater(() -> codeArea.setText(finalResult));
    }
    public void saveFileAction() throws IOException {
        if(currentFile != null) {
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentFile)));
            output.write(codeArea.getText());
            output.close();
        }
    }
    public void saveFileAsAction() throws IOException {
        Stage fileStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        File selectedFile = fileChooser.showSaveDialog(fileStage);

        if(selectedFile == null) return;

        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(selectedFile)));
        output.write(codeArea.getText());
        output.close();

    }
    public void closeAction() {
        System.exit(0);
    }
    public void run() {
        if(state == State.INTERPRETER) {
            interpreter.run(codePreparer.run());
        }
        else {
            debugger.prepare(codePreparer.run());
        }
    }

    public void setDebuggerMode() {
        tapePane.setMaxHeight(100);
        tapePane.setMinHeight(100);

        state = State.DEBUGGER;
        modeMenu.setText("Debugger");
        runButton.setText("Prepare");
    }
    public void setInterpreterMode() {
        tapePane.setMaxHeight(0);
        tapePane.setMinHeight(0);

        state = State.INTERPRETER;
        modeMenu.setText("Interpreter");
        runButton.setText("Run");
    }

    public void next() {
        debugger.run();
    }
    public void nextStep() {
        debugger.singleStep();
    }
}
