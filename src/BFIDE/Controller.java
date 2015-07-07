package BFIDE;

import BFIDE.Breakpoints.BreakpointSettingController;
import BFIDE.Converter.ConverterController;
import BFIDE.FXIO.FXInput;
import BFIDE.FXIO.FXLogger;
import BFIDE.FXIO.FXOutput;
import BFIDE.HeartOfEverything.Debugger;
import BFIDE.HeartOfEverything.Interpreter;
import BFIDE.Parser.CodePreparer;
import BFIDE.Parser.ParserSettingController;
import BFIDE.Parser.SimpleParser;
import BFIDE.Tape.BFNode;
import BFIDE.Tape.Tape;
import BFIDE.Tape.TapeCaretaker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;


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
    HBox codeTapeView;
    @FXML
    HBox memoryTapeView;

    @FXML
    MenuButton modeMenu;
    @FXML
    Button runButton;

    File currentFile = null;

    CodePreparer codePreparer;
    Debugger debugger;
    Interpreter interpreter;

    TapeCaretaker codeTapeCaretaker, dataTapeCaretaker;
    private Stage consoleStage = null;


    private enum State {DEBUGGER, INTERPRETER}

    State state;

    //stages

    Stage loggerSettingsStage = null;
    Stage parserSettingsStage = null;
    Stage converterStage = null;
    Stage consoleLogStage = null;

    public void initialize() {
        state = State.INTERPRETER;

        codePreparer = new CodePreparer(codeArea);
        codePreparer.setParser(new SimpleParser());

        Tape interpreterCodeTape = new Tape();
        Tape interpreterMemoryTape = new Tape();
        interpreter = new Interpreter(new FXInput(inputArea), new FXOutput(outputArea), new FXLogger(), interpreterCodeTape, interpreterMemoryTape);

        Tape debuggerCodeTape = new Tape();
        Tape debuggerMemoryTape = new Tape();
        codeTapeCaretaker = new TapeCaretaker(codeTapeView,debuggerCodeTape);
        dataTapeCaretaker = new TapeCaretaker(memoryTapeView,debuggerMemoryTape);
        debugger = new Debugger(new FXInput(inputArea), new FXOutput(outputArea), new FXLogger(), debuggerCodeTape, debuggerMemoryTape);
    }

    public void openFileAction() throws IOException {
        Stage fileStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        File selectedFile = fileChooser.showOpenDialog(fileStage);

        if(selectedFile == null) return;

        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)));

        final StringBuilder result = new StringBuilder();
        input.lines().forEach(x -> result.append(x).append("\n"));
        input.close();

        final String finalResult = String.valueOf(result);

        Platform.runLater(() -> codeArea.setText(finalResult));
    }
    public void saveFileAction() throws IOException {
        if(currentFile != null) {
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentFile)));
            output.write(codeArea.getText());
            output.close();
        } else {
            saveFileAsAction();
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
            Thread t = new Thread(() -> {
                try {
                    interpreter.prepare(codePreparer.run());
                    interpreter.run();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        else try {
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Breakpoints/breakpointSetter.fxml").openStream());
            Stage loggerSettingsStage = new Stage();
            loggerSettingsStage.setScene(new Scene(root));
            loggerSettingsStage.setTitle("Mark breakpoints");
            Thread t = new Thread(() -> {
                List<BFNode> nodes = null;
                try {
                    nodes = codePreparer.run();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                ((BreakpointSettingController) loader.getController()).init(nodes, x -> {
                });
                final List<BFNode> finalNodes = nodes;
                ((BreakpointSettingController) loader.getController()).setLastAction(() -> debugger.prepare(finalNodes));
                Platform.runLater(loggerSettingsStage::show);
            });
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConsole() {
        if(consoleStage == null) {
            try {
                Pane root = FXMLLoader.load(getClass().getResource("Logging/logConsole.fxml"));
                Stage consoleStage = new Stage();
                consoleStage.setScene(new Scene(root, 600, 400));
                consoleStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            consoleStage.show();
        }
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

    public void convert() throws IOException {
        if(converterStage != null) {
            converterStage.show();
        } else try {
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Converter/converter.fxml").openStream());
            converterStage = new Stage();
            converterStage.setScene(new Scene(root));
            converterStage.show();
            ((ConverterController) loader.getController()).init(loggerSettingsStage,codeArea);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void next() {
        new Thread(debugger::run).start();
    }
    public void nextStep() {
        new Thread(debugger::singleStep).start();
    }

    public void showLoggerSettings() {
        if(loggerSettingsStage != null) {
            loggerSettingsStage.show();
        }
        else try {
            Pane root = FXMLLoader.load(getClass().getResource("Logging/loggerSettings.fxml"));
            loggerSettingsStage = new Stage();
            loggerSettingsStage.setScene(new Scene(root));
            loggerSettingsStage.setTitle("Logger settings");
            loggerSettingsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showParserSettings() {
        if(parserSettingsStage != null) {
            parserSettingsStage.show();
        }
        else try {
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Parser/parserSettings.fxml").openStream());
            parserSettingsStage = new Stage();
            parserSettingsStage.setScene(new Scene(root));
            parserSettingsStage.setTitle("Parser settings");
            parserSettingsStage.show();
            ((ParserSettingController) loader.getController()).setLastAction(codePreparer::setParser);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
