package test;

import BFIDE.BFNode;
import BFIDE.BiDirStream;
import BFIDE.FXIO;
import BFIDE.Interpreter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class InterpreterTest {
    @Test
    public void testSimpleProgram() throws Exception {
        BiDirStream stream = mock(FXIO.class);

        when(stream.getText()).thenReturn("a");

        List<BFNode> mockedCode = mock(ArrayList.class);

        when(mockedCode.get(0)).thenReturn(new BFNode(','));
        when(mockedCode.get(1)).thenReturn(new BFNode('.'));

        when(mockedCode.size()).thenReturn(new Integer(2));

        Interpreter testedObj = new Interpreter(stream);
        testedObj.run(mockedCode);

        verify(stream).setText("a");
    }

    @Test
    public void testSimpleProgram2() throws Exception {
        BiDirStream stream = mock(FXIO.class);

        when(stream.getText()).thenReturn("ab");

        List<BFNode> mockedCode = mock(ArrayList.class);

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
}