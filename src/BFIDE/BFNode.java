package BFIDE;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by michaziobro on 26.06.2015.
 */
public class BFNode {
    static final Character[] LEGAL_TYPES = {'>', '<', ',', '.', '+', '-', '[', ']'};
    public static final Set<Character> LegalTypes = new TreeSet<>(Arrays.asList(LEGAL_TYPES));

    private final Character type;
    private Integer jump;

    public void setJump(Integer jump) {
        this.jump = jump;
    }
    public Integer getJump() {
        return jump;
    }

    public char getType() {
        return type;
    }
    public BFNode(char type) {
        if(LegalTypes.contains(type)) {
            this.type = type;
            return;
        }

        assert false : "This place shouldn't be reached";
        this.type = 'a';
    }
}
