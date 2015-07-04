package BFIDE.HeartOfEverything;

import BFIDE.BFNode;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Tape;
import BFIDE.TypeBox;
import BFIDE.UIMessages;

import java.util.List;

public class Interpreter extends Executor {

    public Interpreter(InputWrapper input, OutputWrapper output, LoggerWrapper logger, Tape<BFNode> codeTape, Tape<TypeBox> memoryTape) {
        super(input, output, logger, codeTape, memoryTape);
    }

    public void prepare(List<BFNode> nodes) {
        super.prepare(nodes);
    }

    public void run() {
        while(codeTape.getPosition() < codeTape.getLength()) {
            step();
        }

        logger.alert(UIMessages.programEnded);
    }
}
