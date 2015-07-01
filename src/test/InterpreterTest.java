package test;

import BFIDE.*;
import BFIDE.FXIO.FXInput;
import BFIDE.FXIO.FXLogger;
import BFIDE.FXIO.FXOutput;
import BFIDE.HeartOfEverything.Executor;
import BFIDE.HeartOfEverything.Interpreter;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
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
    Executor testedObj;

    @Before
    public void mockStreamAndList() {
        in = mock(FXInput.class);
        out = mock(FXOutput.class);
        logger = mock(FXLogger.class);
        mockedCode = mock(ArrayList.class);
        testedObj = new Interpreter(in, out, logger);
    }

    @Test
    public void testSimpleProgram() throws Exception {
        when(in.getText()).thenReturn("a");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(2));

        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(out).setText("a");
        verify(logger).alert(UIMessages.programEnded);
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

        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(out).setText("ba");
        verify(logger).alert(UIMessages.programEnded);
    }

    @Test
    public void testLeftBoundCheck() throws Exception {
        when(in.getText()).thenReturn("");

        when(mockedCode.get(0)).thenReturn(new BFNode('<'));

        when(mockedCode.size()).thenReturn(new Integer(1));

        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(logger).alert(UIMessages.negIndexes);
    }

    @Test
    public void testRightBoundCheck() throws Exception {
        when(in.getText()).thenReturn("");

        when(mockedCode.size()).thenReturn(testedObj.tapeSize());

        when(mockedCode.get(any(Integer.class))).thenReturn(new BFNode('>'));

        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(logger).alert(UIMessages.outOfTape);
    }

    @Test
    public void testTapeSize() throws Exception {
        when(in.getText()).thenReturn("");

        when(mockedCode.size()).thenReturn(testedObj.tapeSize() - 1);

        when(mockedCode.get(any(Integer.class))).thenReturn(new BFNode('>'));

        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(logger, never()).alert(UIMessages.outOfTape);
    }

    @Test
    public void testInsufficientInput() throws Exception {
        when(in.getText()).thenReturn("");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));

        when(mockedCode.size()).thenReturn(new Integer(1));

        Interpreter testedObj = new Interpreter(in, out, logger);
        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(logger).alert(UIMessages.noInput);
    }
}