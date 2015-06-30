package BFIDE;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ConverterController {

    static public ConverterController me;

    private enum State {NONE, C, ASSEMBLER}

    State state = State.NONE;
    TextArea codeArea;

    @FXML
    MenuButton languageMenu;

    Stage stage;

    public ConverterController() {
        me = this;
    }

    public void init(Stage stage, TextArea textArea) {
        this.stage = stage;
        this.codeArea = textArea;
    }

    public void chooseC() {
        state = State.C;
        languageMenu.setText("C");
    }
    public void chooseAssembler() {
        state = State.ASSEMBLER;
        languageMenu.setText("Assembler");
    }

    public void ok() {
        ConverterBuilderDirector director;
        Converter converter;

        switch(state) {
            case NONE:
                stage.close();
                return;
            case C:
                director = new ConverterBuilderDirector(new CConverterBuilder());
                director.construct();
                converter = director.getResult();
                codeArea.setText(converter.convert(codeArea.getText()));
                stage.close();
                return;
            case ASSEMBLER:
                director = new ConverterBuilderDirector(new ASMConverterBuilder());
                director.construct();
                converter = director.getResult();
                codeArea.setText(converter.convert(codeArea.getText()));
                stage.close();
        }
    }
    public void cancel() {
        stage.close();
    }
}
