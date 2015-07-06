package test;

import BFIDE.Tape.BFNode;
import BFIDE.Parser.SimpleParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleParserTest {
    SimpleParser parser = new SimpleParser();
    @Test
    public void testArbitraryCharacters() {
        List<BFNode> test_result = parser.parse("HKHHJJKGGK*=");
        assertEquals("Parser does not ignore characters correctly", test_result, new ArrayList<BFNode>());
    }

    @Test
    public void testJumpAssigments() {
        List<BFNode> test_result = parser.parse("[+af][asdf[,-].]");
        assertEquals("Jumps pointers are not assigned correctly 0", test_result.get(0).getJump(), new Integer(2));
        assertEquals("Jumps pointers are not assigned correctly 1", test_result.get(2).getJump(), new Integer(0));
        assertEquals("Jumps pointers are not assigned correctly 2", test_result.get(3).getJump(), new Integer(9));
        assertEquals("Jumps pointers are not assigned correctly 3", test_result.get(9).getJump(), new Integer(3));
        assertEquals("Jumps pointers are not assigned correctly 4", test_result.get(4).getJump(), new Integer(7));
        assertEquals("Jumps pointers are not assigned correctly 5", test_result.get(7).getJump(), new Integer(4));
    }
}