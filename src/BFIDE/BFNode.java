package BFIDE;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class BFNode extends TypeBox {
    static final Character[] LEGAL_TYPES = {'>', '<', ',', '.', '+', '-', '[', ']'};
    public static final Set<Character> LegalTypes = new TreeSet<>(Arrays.asList(LEGAL_TYPES));

    private boolean breakpoint = false;

    public void setBreakpoint(boolean breakpoint) {
        this.breakpoint = breakpoint;
    }
    public boolean isBreakpoint() {
        return breakpoint;
    }

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
        super(type);

        assert LegalTypes.contains(type) : "This place shouldn't be reached";
    }
}
