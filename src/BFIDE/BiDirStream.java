package BFIDE;

import java.util.concurrent.ExecutionException;

public interface BiDirStream {
    String getText() throws ExecutionException, InterruptedException;
    void setText(String text);
}
