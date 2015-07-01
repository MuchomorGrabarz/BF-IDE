package test;

import BFIDE.BFNode;
import BFIDE.FXIO.FXInput;
import BFIDE.FXIO.FXLogger;
import BFIDE.FXIO.FXOutput;
import BFIDE.HeartOfEverything.Debugger;
import BFIDE.IOWrapper.InputWrapper;
import BFIDE.IOWrapper.LoggerWrapper;
import BFIDE.IOWrapper.OutputWrapper;
import BFIDE.UIMessages;
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
    List<BFNode> mockedCode;
    Debugger testedObj;

    @Before
    public void mockStreamAndList() {
        in = mock(FXInput.class);
        out = mock(FXOutput.class);
        logger = mock(FXLogger.class);
        mockedCode = mock(ArrayList.class);
        testedObj = new Debugger(in, out, logger);
    }

    @Test
    public void testSimpleProgramInteraction() throws Exception {
        when(in.getText()).thenReturn("a");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(2));

        //BAD TEST

        testedObj.prepare(mockedCode);
        verify(out, times(1)).setText(""); //not so behavioral test, doubtfully good
        testedObj.singleStep();
        verify(out, times(2)).setText("");
        testedObj.singleStep();
        verify(out, times(1)).setText("a");
        verify(out, times(3)).setText(anyString());

        verify(logger, times(1)).alert(UIMessages.programEnded);
    }

    @Test
    public void testSimpleProgramInteraction2() throws Exception {
        when(in.getText()).thenReturn("ab");

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('>'));
        when(mockedCode.get(2)).thenReturn(new BFNode(','));
        when(mockedCode.get(3)).thenReturn(new BFNode('.'));
        when(mockedCode.get(4)).thenReturn(new BFNode('<'));
        when(mockedCode.get(5)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(6));

        //BAD TEST

        testedObj.prepare(mockedCode);
        verify(out, times(1)).setText(""); //not so behavioral test, doubtfully good
        testedObj.singleStep();
        verify(out, times(2)).setText("");
        testedObj.singleStep();
        verify(out, times(3)).setText("");
        testedObj.singleStep();
        verify(out, times(4)).setText("");
        testedObj.singleStep();
        verify(out, times(1)).setText("b");
        testedObj.singleStep();
        verify(out, times(2)).setText("b");
        testedObj.singleStep();
        verify(out, times(1)).setText("ba");

        verify(logger, times(1)).alert(UIMessages.programEnded);
        verify(logger, times(1)).alert(anyString());
    }
}