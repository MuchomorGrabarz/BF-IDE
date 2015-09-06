package test;

import BFIDE.Parser.ParserIgnoringComments;
import BFIDE.Tape.BFNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParsersIgnoringCommentsTest {
    /*
    Not included in the test suite - serves only as a base for other tests.

    Should be tested internally and not via the Parser interface in my (Szymon)
    mind, but that is not possible with the current structure of the parsers.
    Perhaps a thing to change so - TODO .
     */
    ParserIgnoringComments parser;
    String commentMark;
    ArrayList<String> splittedCode = new ArrayList<String>(Arrays.asList("[+af]", "[asdf[,-].]\n+"));

    @Test
    public void testJumpAssigmentsEtc() {
        List<BFNode> test_result = parser.parse(String.join(commentMark, splittedCode));
        assertEquals("Jumps pointers are not assigned correctly 0", test_result.get(0).getJump(), new Integer(2));
        assertEquals("Jumps pointers are not assigned correctly 1", test_result.get(2).getJump(), new Integer(0));
        assertEquals("The comment does not seem to end in the right place", test_result.get(3).getType(), '+');
    }
}
