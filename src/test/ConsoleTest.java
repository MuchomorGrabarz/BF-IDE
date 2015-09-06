package test;

import BFIDE.Listener;
import BFIDE.Logging.Console;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConsoleTest {

    Console console;

    @Mock
    private Listener mockedListener1;
    @Mock
    private Listener mockedListener2;

    @Before
    public void prepareMocks() {
        mockedListener1 = Mockito.mock(Listener.class);
        mockedListener2 = Mockito.mock(Listener.class);
        console = Console.getConsole();
    }

    @Test
    public void logWithListeners() {
        console.registerListener(mockedListener1);
        console.registerListener(mockedListener2);

        console.log("Simple log.");

        Mockito.verify(mockedListener1, Mockito.times(1)).punch();
        Mockito.verify(mockedListener2, Mockito.times(1)).punch();
    }

    @Test
    public void logWithoutListeners() {
        console.log("No listeners yet.");

        console.registerListener(mockedListener1);
        console.registerListener(mockedListener2);

        Mockito.verify(mockedListener1, Mockito.times(0)).punch();
        Mockito.verify(mockedListener2, Mockito.times(0)).punch();

        console.log("Now someone listens.");

        Mockito.verify(mockedListener1, Mockito.times(1)).punch();
        Mockito.verify(mockedListener2, Mockito.times(1)).punch();
    }

    @Test
    public void gettingLogs() {
        console.clear();

        console.log("First");
        console.log("Second");

        List<String> l = console.getLogs();

        assertEquals("Amount of loggs gathered is not correct", l.size(), 2);

        assertEquals("First log is not correct", l.get(0), "First");
        assertEquals("Second log is not correct", l.get(1), "Second");
    }
}
