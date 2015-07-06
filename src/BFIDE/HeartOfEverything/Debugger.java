package BFIDE.HeartOfEverything;

import BFIDE.Tape.BFNode;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Tape.Tape;
import BFIDE.Logging.UIMessages;

import java.util.List;

public class Debugger extends Executor {

    public Debugger(InputWrapper input, OutputWrapper output, LoggerWrapper logger, Tape codeTape, Tape memoryTape) {
        super(input, output, logger, codeTape, memoryTape);
    }

    public void prepare(List<BFNode> nodes) {
        super.prepare(nodes);
    }

    public void singleStep() {

        if(codeTape.getPosition() >= codeTape.getLength()) {
            logger.warningAlert(UIMessages.programEnded);
            return;
        }
        if(memoryTape.getPosition() < 0) {
            logger.warningAlert(UIMessages.illegaState);
            return;
        }
        if(memoryTape.getPosition() >= memoryTape.getLength()) {
            logger.warningAlert(UIMessages.illegaState);
            return;
        }

        step();
        if(codeTape.getPosition() == codeTape.getLength())
            logger.infoAlert(UIMessages.programEnded);
    }
    public void run() {

        if(codeTape.getPosition() >= codeTape.getLength()) {
            logger.warningAlert(UIMessages.alreadyEnded);
            return;
        }
        do step(); while(codeTape.getPosition() < codeTape.getLength() && !codeTape.getValue().isBreakpoint());

        logger.infoAlert(UIMessages.programEnded);
    }
}
