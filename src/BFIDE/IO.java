package BFIDE;

import java.util.concurrent.ExecutionException;

public interface IO {
    String getText() throws ExecutionException, InterruptedException;
    void setText(String text);
    void alert(String text);
}
