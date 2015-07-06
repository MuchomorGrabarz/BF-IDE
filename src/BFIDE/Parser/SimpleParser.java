package BFIDE.Parser;

import BFIDE.Tape.BFNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleParser implements Parser {
    @Override
    public List<BFNode> parse(String code) {
        ArrayList<BFNode> result = new ArrayList<>();
        LinkedList<Integer> stack = new LinkedList<>();

        int pos = 0;

        for(char c : code.toCharArray()) if(BFNode.LegalTypes.contains(c)) {
            BFNode newOne = new BFNode(c);

            if(c == '[') stack.addFirst(pos);
            if(c == ']') {
                newOne.setJump(stack.pollFirst());
                result.get(newOne.getJump()).setJump(pos);
            }

            result.add(newOne);
            pos++;
        }
        return result;
    }
}
