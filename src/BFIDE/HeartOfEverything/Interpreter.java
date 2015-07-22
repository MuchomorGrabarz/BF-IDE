package BFIDE.HeartOfEverything;

import BFIDE.Tape.BFNode;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Tape.Tape;
import BFIDE.Logging.UIMessages;

import java.util.List;

public class Interpreter extends Executor {

    public Interpreter(InputWrapper input, OutputWrapper output, LoggerWrapper logger, Tape codeTape, Tape memoryTape) {
        super(input, output, logger, codeTape, memoryTape);
    }

    public void prepare(List<BFNode> nodes) {
        super.prepare(nodes);
    }

    public void run() {
        while(!cancelled && codeTape.getPosition() < codeTape.getLength()) {
            step();
        }

        logger.infoAlert(UIMessages.programEnded);
    }
}
