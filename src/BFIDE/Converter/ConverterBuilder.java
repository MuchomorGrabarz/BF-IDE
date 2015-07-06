package BFIDE.Converter;

public abstract class ConverterBuilder {
    Converter myConverter;

    abstract public void setPlusConversion();
    abstract public void setMinusConversion();
    abstract public void setLeftBracketConversion();
    abstract public void setRightBracketConversion();
    abstract public void setGoLeftConversion();
    abstract public void setGoRightConversion();
    abstract public void setDotConversion();

    public abstract void setColonConversion();

    abstract public void setBeginning();
    abstract public void setEnding();

    public ConverterBuilder() {
        myConverter = new Converter();
    }
    public Converter getResult() {
        return myConverter;
    }
}
