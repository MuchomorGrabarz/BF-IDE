package BFIDE.IOWrapper;

public interface InputWrapper {
    void reset();

    boolean hasNext();
    char getChar();
    String getText();
}
