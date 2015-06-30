package test;

import BFIDE.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class InterpreterTest {
    InputWrapper in;
    OutputWrapper out;
    LoggerWrapper logger;
    List<BFNode> mockedCode;

    @Before
    public void mockStreamAndList() {
        in = mock(FXInput.class);
        out = mock(FXOutput.class);
        logger = mock(FXLogger.class);
        mockedCode = mock(ArrayList.class);
    }

    @Test
    public void testSimpleProgram() throws Exception {
        when(in.getText()).thenReturn("a");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(2));

        Interpreter testedObj = new Interpreter(in, out, logger);
        testedObj.run(mockedCode);

        verify(out).setText("a");
    }

    @Test
    public void testSimpleProgram2() throws Exception {
        when(in.getText()).thenReturn("ab");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('>'));
        when(mockedCode.get(2)).thenReturn(new BFNode(','));
        when(mockedCode.get(3)).thenReturn(new BFNode('.'));
        when(mockedCode.get(4)).thenReturn(new BFNode('<'));
        when(mockedCode.get(5)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(6));

        Interpreter testedObj = new Interpreter(in, out, logger);
        testedObj.run(mockedCode);

        verify(out).setText("ba");
    }

    @Test
    public void testLeftBoundCheck() throws Exception {
        when(in.getText()).thenReturn("");

        when(mockedCode.get(0)).thenReturn(new BFNode('<'));

        when(mockedCode.size()).thenReturn(new Integer(1));

        Interpreter testedObj = new Interpreter(in, out, logger);
        testedObj.run(mockedCode);

        verify(logger).alert("Program moved to negative tape indexes");
    }

    @Test
    public void testRightBoundCheck() throws Exception {
        when(in.getText()).thenReturn("");

        Interpreter testObj = new Interpreter(in, out, logger);

        when(mockedCode.size()).thenReturn(testObj.tapeSize);

        when(mockedCode.get(any(Integer.class))).thenReturn(new BFNode('>'));

        testObj.run(mockedCode);

        verify(logger).alert("Program went over the (" + String.valueOf(testObj.tapeSize) + ") tape size limit");
    }

    @Test
    public void testTapeSize() throws Exception {
        when(in.getText()).thenReturn("");

        Interpreter testObj = new Interpreter(in, out, logger);

        when(mockedCode.size()).thenReturn(testObj.tapeSize - 1);

        when(mockedCode.get(any(Integer.class))).thenReturn(new BFNode('>'));

        testObj.run(mockedCode);

        verify(logger, never()).alert("Program went over the (" + String.valueOf(testObj.tapeSize) + ") tape size limit");
    }

    @Test
    public void testInsufficientInput() throws Exception {
        when(in.getText()).thenReturn("");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));

        when(mockedCode.size()).thenReturn(new Integer(1));

        Interpreter testedObj = new Interpreter(in, out, logger);
        testedObj.run(mockedCode);

        verify(logger).alert("Insufficient input given");
    }
}