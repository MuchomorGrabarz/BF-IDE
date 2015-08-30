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

    @Test
    public void testParsingSampleCodeWithLinebreaks() {
        List<BFNode> test_result = parser.parse(">>\nASDCS\n>,Bppp<k.\n+B-A");

        assertEquals("Incorrect size of list of nodes", test_result.size(), 8);

        assertEquals("Type of 1 node is not correct", test_result.get(0).getType(), '>');
        assertEquals("Type of 2 node is not correct", test_result.get(1).getType(), '>');
        assertEquals("Type of 3 node is not correct", test_result.get(2).getType(), '>');
        assertEquals("Type of 4 node is not correct", test_result.get(3).getType(), ',');
        assertEquals("Type of 5 node is not correct", test_result.get(4).getType(), '<');
        assertEquals("Type of 6 node is not correct", test_result.get(5).getType(), '.');
        assertEquals("Type of 7 node is not correct", test_result.get(6).getType(), '+');
        assertEquals("Type of 8 node is not correct", test_result.get(7).getType(), '-');
    }
}