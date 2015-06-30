package BFIDE;

public class CConverterBuilder extends ConverterBuilder {
    @Override
    public void setPlusConversion() {
        myConverter.setPlus("tab[pos]++;\n");
    }
    @Override
    public void setMinusConversion() {
        myConverter.setPlus("tab[pos]--;\n");
    }
    @Override
    public void setLeftBracketConversion() {
        myConverter.setLeftBracket("while(tab[pos]) {\n");
    }
    @Override
    public void setRightBracketConversion() {
        myConverter.setRightBracket("}\n");
    }
    @Override
    public void setGoLeftConversion() {
        myConverter.setGoLeft("pos++;\n");
    }
    @Override
    public void setGoRightConversion() {
        myConverter.setGoRight("pos--;\n");
    }
    @Override
    public void setDotConversion() {
        myConverter.setDot("scanf(\"%c\", &tab[pos]);\n");
    }
    @Override
    public void setColonConversion() {
        myConverter.setColon("printf(\"%c\", tab[pos]);\n");
    }
    @Override
    public void setBeginning() {
        myConverter.setBeginning("#include <stdio.h>\n\nchar tab[30000] = {0};\n\nint main() {\n\tint pos = 0;\n\n");
    }
    @Override
    public void setEnding() {
        myConverter.setEnding("}\n");
    }
}
