package BFIDE.Parser;

import BFIDE.Tape.BFNode;

import java.util.*;

public interface Parser {
    List<BFNode> parse(String code);
}
