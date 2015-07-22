package test;

import BFIDE.Tape.BFNode;
import BFIDE.Tape.Tape;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TapeTest {
    private Tape testObj;
    public List<BFNode> mockedCode;

    @Before
    public void createTapeAndMockOthers() {
        testObj = new Tape();
    }

    @Test
    public void testBasicTapeBehavior() {
        List<BFNode> mockedCode = new ArrayList<>();
        mockedCode.add(new BFNode('+'));
        mockedCode.add(new BFNode('-'));

        testObj.reset(mockedCode);

        assertEquals("Tape length does not match code list length", 2, testObj.getLength());
        assertEquals("Initial position not set to 0", 0, testObj.getPosition());

        assertEquals("Value on the first tape tile is incorrect", new BFNode('+').getType(), testObj.getValue().getType());
        testObj.setPosition(1);
        assertEquals("Value on the second tape tile is incorrect or setPosition malfunctions", new BFNode('-').getType(), testObj.getValue().getType());
        testObj.setValue(new BFNode('+'));
        assertEquals("Changing value on a tape tile does not work", (new BFNode('+')).getType(), testObj.getValue().getType());
    }
}