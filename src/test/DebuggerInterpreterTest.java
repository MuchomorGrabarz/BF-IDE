package test;

import BFIDE.FXIO.FXInput;
import BFIDE.FXIO.FXLogger;
import BFIDE.FXIO.FXOutput;
import BFIDE.HeartOfEverything.Debugger;
import BFIDE.Tape.Tape;
import org.junit.Before;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class DebuggerInterpreterTest extends InterpreterTest {
    /*
        Debugger is a superset of Interpreter so it should behave
        the same on interpreters test suit, and thus inherits it.
        The below tests are additional tests which aim to test
        the debugger specific behavior. Keep in mind that
        the test setUp method and some field are inherited,
        be cautious reading these tests.
     */

    @Before
    public void mockStreamAndList() {
        in = mock(FXInput.class);
        out = mock(FXOutput.class);
        logger = mock(FXLogger.class);
        mockedCode = mock(ArrayList.class);

        codeTape = new Tape(); // TODO very bad, to change
        memoryTape = new Tape(); // TODO as above - needs changing

        testedObj = new Debugger(in, out, logger,codeTape,memoryTape);
    }

}