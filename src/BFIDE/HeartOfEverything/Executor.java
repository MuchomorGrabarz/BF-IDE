package BFIDE.HeartOfEverything;

import BFIDE.BFNode;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Tape;
import BFIDE.TypeBox;
import BFIDE.UIMessages;

import java.util.List;

public abstract class Executor {

    // En Taro Tassadar!

    protected Tape<BFNode> codeTape;
    protected Tape<TypeBox> memoryTape;

    protected InputWrapper input;
    protected OutputWrapper output;
    protected LoggerWrapper logger;

    private Integer tapeSize = 30000;
    private List<TypeBox> initTab;

    public Executor(InputWrapper input, OutputWrapper output, LoggerWrapper logger, Tape<BFNode> codeTape, Tape<TypeBox> memoryTape) {
        this.codeTape = codeTape;
        this.memoryTape = memoryTape;
        this.input = input;
        this.output = output;
        this.logger = logger;
    }

    public Tape getCodeTape() {return codeTape;}
    public Tape getMemoryTape() {return memoryTape;}

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
                    logger.alert(UIMessages.noInput);
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
            logger.alert(UIMessages.negIndexes);
            return;
        }

        if(memoryTape.getPosition() >= tapeSize) {
            logger.alert(UIMessages.outOfTape);
            return;
        }

    }

    Integer tapeSize() {
        return tapeSize;
    }
    void prepare(List<BFNode> code) {
        input.reset();
        output.reset();
        codeTape.reset(code);

        initTab.clear();
        for(int i = 0; i<= tapeSize; i++) {
            initTab.add(new TypeBox('0'));
        }
        memoryTape.reset(initTab);
    }
    abstract void run();
}
