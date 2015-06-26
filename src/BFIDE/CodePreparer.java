package BFIDE;

import javafx.scene.control.TextInputControl;

import java.util.List;

/**
 * Created by michaziobro on 26.06.2015.
 */
public class CodePreparer {
    private Parser parser;
    public void setParser(Parser parser) {this.parser = parser;}

    private TextInputControl code;

    public CodePreparer(TextInputControl code) {
        this.code = code;
    }

    public List<BFNode> run() {
        return parser.parse(code.getText());
    }
}
