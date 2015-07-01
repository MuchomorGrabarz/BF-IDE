package BFIDE.HeartOfEverything;

import BFIDE.BFNode;

import java.util.List;

public interface Executor {
    Integer tapeSize();
    void prepare(List<BFNode> code);
    void run();
}
