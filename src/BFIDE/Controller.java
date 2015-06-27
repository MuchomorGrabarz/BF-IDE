package BFIDE;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.concurrent.ExecutionException;


public class Controller {

    static Controller me;

    @FXML
    TextArea codeArea;
    @FXML
    TextArea inputArea;
    @FXML
    TextArea outputArea;

    @FXML
    Pane tapePane;
    @FXML
    HBox codeTape;
    @FXML
    HBox dataTape;

    @FXML
    MenuButton modeMenu;

    @FXML
    Button runButton;

    File currentFile = null;

    CodePreparer codePreparer;
    Debugger debugger;
    Interpreter interpreter;

    TapeCaretaker codeTapeCaretaker;
    TapeCaretaker dataTapeCaretaker;

    private enum State {DEBUGGER, INTERPRETER};
    State state;

    public Controller() {
        me = this;
    }

    public void init() {
        state = State.INTERPRETER;

        codePreparer = new CodePreparer(codeArea);
        codePreparer.setParser(new SimpleParser());
        debugger = new Debugger(inputArea, outputArea);
        interpreter = new Interpreter(inputArea, outputArea);

        codeTapeCaretaker = new TapeCaretaker(codeTape,debugger);
        codeTapeCaretaker.setState(TapeCaretaker.State.CODE);

        dataTapeCaretaker = new TapeCaretaker(dataTape,debugger);
        dataTapeCaretaker.setState(TapeCaretaker.State.DATA);
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
        Thread t = new Thread(() -> {if(state == State.INTERPRETER) {
            try {
                interpreter.run(codePreparer.run());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                debugger.prepare(codePreparer.run());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }});
        t.start();
    }

    public void setDebuggerMode() {
        tapePane.setMaxHeight(150);
        tapePane.setMinHeight(150);

        state = State.DEBUGGER;
        Platform.runLater(() -> modeMenu.setText("Debugger"));
        Platform.runLater(() -> runButton.setText("Prepare"));
    }
    public void setInterpreterMode() {
        tapePane.setMaxHeight(0);
        tapePane.setMinHeight(0);

        state = State.INTERPRETER;
        Platform.runLater(() -> modeMenu.setText("Interpreter"));
        Platform.runLater(() -> runButton.setText("Run"));
    }

    public void next() {
        new Thread(() -> debugger.run()).start();
    }
    public void nextStep() {
        new Thread(() -> debugger.singleStep()).start();
    }
}
