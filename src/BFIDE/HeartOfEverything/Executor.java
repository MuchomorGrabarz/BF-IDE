package BFIDE.HeartOfEverything;

import BFIDE.Tape.BFNode;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Tape.Tape;
import BFIDE.Logging.UIMessages;

import java.util.LinkedList;
import java.util.List;

public abstract class Executor {

    // En Taro Tassadar!

    protected Tape codeTape;
    protected Tape memoryTape;

    protected InputWrapper input;
    protected OutputWrapper output;
    protected LoggerWrapper logger;

    private Integer tapeSize = 30000;
    private List<BFNode> initTab;

    protected boolean cancelled = false;

    public Executor(InputWrapper input, OutputWrapper output, LoggerWrapper logger, Tape codeTape, Tape memoryTape) {
        this.codeTape = codeTape;
        this.memoryTape = memoryTape;
        this.input = input;
        this.output = output;
        this.logger = logger;
    }

    protected void step() {
        switch (codeTape.getValue().getType()) {
            case '>':
                memoryTape.setPosition(memoryTape.getPosition()+1);
                break;
            case '<':
                memoryTape.setPosition(memoryTape.getPosition()-1);
                break;
            case '+':
                memoryTape.getValue().setType((char) (((int) memoryTape.getValue().getType() + 1)%256));
                break;
            case '-':
                memoryTape.getValue().setType((char) (((int) memoryTape.getValue().getType() - 1)%256));
                break;
            case ',':
                if(!input.hasNext()) {
                    logger.warningAlert(UIMessages.noInput);
                    cancelled = true;
                    return;
                }
                memoryTape.getValue().setType(input.getChar());
                break;
            case '.':
                output.putChar(memoryTape.getValue().getType());
                break;
            case '[':
                if(memoryTape.getValue().getType() == 0) codeTape.setPosition(codeTape.getValue().getJump());
                break;
            case ']':
                codeTape.setPosition(codeTape.getValue().getJump()-1);
                break;
        }
        codeTape.setPosition(codeTape.getPosition() + 1);

        if(memoryTape.getPosition() < 0) {
            logger.warningAlert(UIMessages.negIndexes);
            cancelled = true;
            return;
        }

        if(memoryTape.getPosition() >= tapeSize) {
            logger.warningAlert(UIMessages.outOfTape);
            cancelled = true;
            return;
        }

    }

    public Integer tapeSize() {
        return tapeSize;
    }
    public void prepare(List<BFNode> code) {
        input.reset();
        output.reset();
        codeTape.reset(code);

        initTab = new LinkedList<>();
        for(int i = 0; i<= tapeSize; i++) {
            initTab.add(new BFNode('0'));
        }
        memoryTape.reset(initTab);
    }
    abstract public void run();
}
