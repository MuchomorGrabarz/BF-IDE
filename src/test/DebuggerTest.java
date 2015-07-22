package test;

import BFIDE.Tape.BFNode;
import BFIDE.FXIO.FXInput;
import BFIDE.FXIO.FXLogger;
import BFIDE.FXIO.FXOutput;
import BFIDE.HeartOfEverything.Debugger;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Tape.Tape;
import BFIDE.Logging.UIMessages;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class DebuggerTest {
    /*
        Tests for Debugger specific stuff.
     */

    InputWrapper in;
    OutputWrapper out;
    LoggerWrapper logger;

    Tape codeTape;
    Tape memoryTape;

    List<BFNode> mockedCode;
    Debugger testedObj;

    @Before
    public void mockStreamAndList() {
        in = mock(FXInput.class);
        out = mock(FXOutput.class);
        logger = mock(FXLogger.class);
        mockedCode = mock(ArrayList.class);

        codeTape = new Tape(); // TODO - very bad, depends highly on other classes
        memoryTape = new Tape(); // TODO

        testedObj = new Debugger(in, out, logger, codeTape, memoryTape);
    }

    @Test
    public void testSimpleProgramInteraction() throws Exception {
        when(in.hasNext()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(in.getChar()).thenReturn('a');

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(2);

        testedObj.prepare(mockedCode);
        verify(out).reset();
        verify(in).reset();
        verify(out, times(0)).putChar(anyChar());
        testedObj.singleStep();
        verify(out, times(0)).putChar(anyChar());
        testedObj.singleStep();
        verify(out, times(1)).putChar('a');
        verify(out, times(1)).putChar(anyChar());

        verify(logger, times(1)).infoAlert(UIMessages.programEnded);
        verify(logger, times(1)).infoAlert(anyString());
    }

    @Test
    public void testSimpleProgramInteraction2() throws Exception {
        when(in.hasNext()).thenReturn(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
        when(in.getChar()).thenReturn('a', 'b');

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('>'));
        when(mockedCode.get(2)).thenReturn(new BFNode(','));
        when(mockedCode.get(3)).thenReturn(new BFNode('.'));
        when(mockedCode.get(4)).thenReturn(new BFNode('<'));
        when(mockedCode.get(5)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(6);

        testedObj.prepare(mockedCode);
        verify(out, times(0)).putChar(anyChar());
        testedObj.singleStep();
        verify(out, times(0)).putChar(anyChar());
        testedObj.singleStep();
        verify(out, times(0)).putChar(anyChar());
        testedObj.singleStep();
        verify(out, times(0)).putChar(anyChar());
        testedObj.singleStep();
        verify(out, times(1)).putChar('b');
        verify(out, times(1)).putChar(anyChar());
        testedObj.singleStep();
        verify(out, times(1)).putChar(anyChar());
        testedObj.singleStep();
        verify(out, times(1)).putChar('a');
        verify(out, times(2)).putChar(anyChar());

        verify(logger, times(1)).infoAlert(UIMessages.programEnded);
        verify(logger, times(1)).infoAlert(anyString());
    }
}