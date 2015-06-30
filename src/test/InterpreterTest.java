package test;

import BFIDE.BFNode;
import BFIDE.FXIO;
import BFIDE.IO;
import BFIDE.Interpreter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class InterpreterTest {
    IO stream;
    List<BFNode> mockedCode;

    @Before
    public void mockStreamAndList() {
        stream = mock(FXIO.class);
        mockedCode = mock(ArrayList.class);
    }

    @Test
    public void testSimpleProgram() throws Exception {
        when(stream.getText()).thenReturn("a");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(2));

        Interpreter testedObj = new Interpreter(stream);
        testedObj.run(mockedCode);

        verify(stream).setText("a");
    }

    @Test
    public void testSimpleProgram2() throws Exception {
        when(stream.getText()).thenReturn("ab");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('>'));
        when(mockedCode.get(2)).thenReturn(new BFNode(','));
        when(mockedCode.get(3)).thenReturn(new BFNode('.'));
        when(mockedCode.get(4)).thenReturn(new BFNode('<'));
        when(mockedCode.get(5)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(6));

        Interpreter testedObj = new Interpreter(stream);
        testedObj.run(mockedCode);

        verify(stream).setText("ba");
    }

    @Test
    public void testLeftBoundCheck() throws Exception {
        when(stream.getText()).thenReturn("");

        when(mockedCode.get(0)).thenReturn(new BFNode('<'));

        when(mockedCode.size()).thenReturn(new Integer(1));

        Interpreter testedObj = new Interpreter(stream);
        testedObj.run(mockedCode);

        verify(stream).alert("Program moved to negative tape indexes");
    }

    @Test
    public void testRightBoundCheck() throws Exception {
        when(stream.getText()).thenReturn("");

        Interpreter testObj = new Interpreter(stream);

        when(mockedCode.size()).thenReturn(testObj.tapeSize);

        when(mockedCode.get(any(Integer.class))).thenReturn(new BFNode('>'));

        testObj.run(mockedCode);

        verify(stream).alert("Program went over the (" + String.valueOf(testObj.tapeSize) + ") tape size limit");
    }

    @Test
    public void testTapeSize() throws Exception {
        when(stream.getText()).thenReturn("");

        Interpreter testObj = new Interpreter(stream);

        when(mockedCode.size()).thenReturn(testObj.tapeSize - 1);

        when(mockedCode.get(any(Integer.class))).thenReturn(new BFNode('>'));

        testObj.run(mockedCode);

        verify(stream, never()).alert("Program went over the (" + String.valueOf(testObj.tapeSize) + ") tape size limit");
    }

    @Test
    public void testInsufficientInput() throws Exception {
        when(stream.getText()).thenReturn("");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));

        when(mockedCode.size()).thenReturn(new Integer(1));

        Interpreter testedObj = new Interpreter(stream);
        testedObj.run(mockedCode);

        verify(stream).alert("Insufficient input given");
    }
}