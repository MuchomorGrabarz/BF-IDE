package BFIDE.Breakpoints;

import BFIDE.Tape.BFNode;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class BreakpointSettingController {

    @FXML
    Pane root;
    @FXML
    VBox breakpointsField;

    private List<BFNode> nodes;
    private Runnable lastAction;

    public void init(List<BFNode> nodes, Consumer<Set<Integer>> consumer) {
        this.nodes = nodes;

        boolean isNewLineNeeded = true;
        HBox currentLine = null;

        for(BFNode node : nodes) {
            if(isNewLineNeeded) {
                currentLine = new HBox();
                breakpointsField.getChildren().add(currentLine);
                isNewLineNeeded = false;
            }

            currentLine.getChildren().add(new ExtendedLabel(String.valueOf(node.getType()),node));

            if(currentLine.getChildren().size() >= 42) isNewLineNeeded = true;

        }
    }

    public void ok() {
        ((Stage) root.getScene().getWindow()).close();

        lastAction.run();
    }
    public void cancel() {
        for(BFNode node : nodes) {
            node.setBreakpoint(false);
        }

        ((Stage) root.getScene().getWindow()).close();
    }

    public void setLastAction(Runnable lastAction) {
        this.lastAction = lastAction;
    }
}
