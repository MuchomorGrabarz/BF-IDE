package BFIDE;

/**
 * Created by michaziobro on 27.06.2015.
 */
public class ParserBuilder {
    /*private boolean cComments;
    private boolean shellCommnets;
    private boolean cobolComments;

    public void setCStyleComments(boolean val) {
        cComments = val;
    }
    public void setShellStyleComments(boolean val) {
        shellCommnets = val;
    }
    public void setCobolStyleComments(boolean val) {
        cobolComments = val;
    }

    public Parser getResult() {
        Set<Character> commentChars = new HashSet<>();

        Function<Object>

        return new Parser() {
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
                } else if(cComments && ) {

                }
                return result;
            }
        };
    }*/
}
