package test;

import BFIDE.BFNode;
import BFIDE.SimpleParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleParserTest {
    SimpleParser parser = new SimpleParser();
    @Test
    public void testArbitraryCharacters() {
        List<BFNode> test_result = parser.parse("HKHHJJKGGK*=");
        assertEquals(test_result, new ArrayList<BFNode>());
    }
}