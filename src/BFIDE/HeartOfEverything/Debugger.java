package BFIDE.HeartOfEverything;

import BFIDE.BFNode;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Tape;
import BFIDE.UIMessages;

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
            logger.alert(UIMessages.programEnded);
            return;
        }
        if(memoryTape.getPosition() < 0) {
            logger.alert(UIMessages.illegaState);
            return;
        }
        if(memoryTape.getPosition() >= memoryTape.getLength()) {
            logger.alert(UIMessages.illegaState);
            return;
        }

        step();
        if(codeTape.getPosition() == codeTape.getLength())
            logger.alert(UIMessages.programEnded);
    }
    public void run() {
        if(memoryTape.getPosition() >= memoryTape.getLength()) {
            logger.alert(UIMessages.alreadyEnded);
            return;
        }

        while(codeTape.getPosition() < codeTape.getLength() && !codeTape.getValue().isBreakpoint()) {
            step();
        }

        logger.alert(UIMessages.programEnded);
    }
}
