package BFIDE.Converter;

import java.util.LinkedList;

public class ASMConverterBuilder extends ConverterBuilder {
    int labelNum = 0;
    LinkedList<Integer> stack = new LinkedList<>();

    @Override
    public void setPlusConversion() {
        myConverter.setPlus("\tadd [RCX],0x1\n");
    }
    @Override
    public void setMinusConversion() {
        myConverter.setMinus("\tsub [RCX],0x1\n");
    }
    @Override
    public void setLeftBracketConversion() {
        myConverter.setLeftBracket("beginLabel" + String.valueOf(labelNum) + ":\n\tmov RAX,[RCX]\n\ttest RAX,RAX\n\tje endLabel" + String.valueOf(labelNum) + "\n");
        stack.addFirst(labelNum);
        labelNum++;
    }
    @Override
    public void setRightBracketConversion() {
        int lastLabelNum = stack.pollFirst();
        myConverter.setRightBracket("\tjmp beginLabel" + String.valueOf(lastLabelNum) + "\n\tendLabel:" + String.valueOf(lastLabelNum) +"\n");
    }
    @Override
    public void setGoLeftConversion() {
        myConverter.setGoLeft("\tdec RCX\n");
    }
    @Override
    public void setGoRightConversion() {
        myConverter.setGoRight("\tinc RCX\n");
    }
    @Override
    public void setDotConversion() {
        myConverter.setDot("");
    }
    @Override
    public void setColonConversion() {
        myConverter.setColon("");
    }

    @Override
    public void setBeginning() {
        myConverter.setBeginning(".data:\n\ttab rxbb 30000\n\n.text:\n\n");
    }
    @Override
    public void setEnding() {
        myConverter.setEnding("");
    }
}
