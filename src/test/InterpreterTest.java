package test;

import BFIDE.FXIO.FXInput;
import BFIDE.FXIO.FXLogger;
import BFIDE.FXIO.FXOutput;
import BFIDE.HeartOfEverything.Executor;
import BFIDE.HeartOfEverything.Interpreter;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.Logging.UIMessages;
import BFIDE.Tape.BFNode;
import BFIDE.Tape.Tape;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class InterpreterTest {
    InputWrapper in;
    OutputWrapper out;
    LoggerWrapper logger;

    Tape codeTape;
    Tape memoryTape;

    List<BFNode> mockedCode;
    Executor testedObj;

    @Before
    public void mockStreamAndList() {
        in = Mockito.mock(FXInput.class);
        out = Mockito.mock(FXOutput.class);
        logger = Mockito.mock(FXLogger.class);
        mockedCode = Mockito.mock(ArrayList.class);

        memoryTape = new Tape(); // TODO, very bad, need changing
        codeTape = new Tape(); // TODO, same as above

        testedObj = new Interpreter(in, out, logger, codeTape, memoryTape);
    }

    @Test
    public void testSimpleProgram() throws Exception {
        when(in.hasNext()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(in.getChar()).thenReturn('a');

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(2));

        testedObj.prepare(mockedCode);
        verify(out).reset();
        verify(in).reset();
        testedObj.run();

        verify(out).putChar('a');
        verify(logger).infoAlert(UIMessages.programEnded);
    }

    @Test
    public void testSimpleProgram2() throws Exception {
        when(in.hasNext()).thenReturn(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
        when(in.getChar()).thenReturn('a', 'b');

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('>'));
        when(mockedCode.get(2)).thenReturn(new BFNode(','));
        when(mockedCode.get(3)).thenReturn(new BFNode('.'));
        when(mockedCode.get(4)).thenReturn(new BFNode('<'));
        when(mockedCode.get(5)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(6));

        testedObj.prepare(mockedCode);
        verify(out).reset();
        verify(in).reset();
        testedObj.run();

        verify(out).putChar('b');
        verify(out).putChar('a');
        verify(logger).infoAlert(UIMessages.programEnded);
    }

    @Test
    public void testLeftBoundCheck() throws Exception {
        when(mockedCode.get(0)).thenReturn(new BFNode('<'));

        when(mockedCode.size()).thenReturn(new Integer(1));

        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(logger).warningAlert(UIMessages.negIndexes);
    }

    @Test
    public void testRightBoundCheck() throws Exception {
        when(mockedCode.size()).thenReturn(testedObj.tapeSize());

        when(mockedCode.get(any(Integer.class))).thenReturn(new BFNode('>'));

        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(logger).warningAlert(UIMessages.outOfTape);
    }

    @Test
    public void testTapeSize() throws Exception {
        when(mockedCode.size()).thenReturn(testedObj.tapeSize() - 1);

        when(mockedCode.get(any(Integer.class))).thenReturn(new BFNode('>'));

        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(logger, never()).warningAlert(UIMessages.outOfTape);
    }

    @Test
    public void testInsufficientInput() throws Exception {
        when(in.hasNext()).thenReturn(Boolean.FALSE);

        when(mockedCode.get(0)).thenReturn(new BFNode(','));

        when(mockedCode.size()).thenReturn(new Integer(1));

        Interpreter testedObj = new Interpreter(in, out, logger, codeTape, memoryTape);
        testedObj.prepare(mockedCode);
        testedObj.run();

        verify(logger).warningAlert(UIMessages.noInput);
    }
}