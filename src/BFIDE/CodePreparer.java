package BFIDE;

import javafx.application.Platform;
import javafx.scene.control.TextInputControl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CodePreparer {
    private Parser parser;
    public void setParser(Parser parser) {this.parser = parser;}

    private TextInputControl code;

    public CodePreparer(TextInputControl code) {
        this.code = code;
    }

    public List<BFNode> run() throws ExecutionException, InterruptedException {
        FutureTask<String> gibCode = new FutureTask<String>(() -> code.getText());
        Platform.runLater(gibCode);
        return parser.parse(gibCode.get());
    }
}
