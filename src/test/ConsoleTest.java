package test;

import BFIDE.Listener;
import BFIDE.Logging.Console;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

public class ConsoleTest {

    Console console = Console.getConsole();

    @Mock
    private Listener mockedListener1;
    @Mock
    private Listener mockedListener2;

    @Before
    public void prepareMocks() {
        mockedListener1 = Mockito.mock(Listener.class);
        mockedListener2 = Mockito.mock(Listener.class);
    }

    @Test
    public void logWithoutListeners() {
        console.log("Simple log.");
    }

    @Test
    public void logWithListeners() {
        console.registerListener(mockedListener1);
        console.registerListener(mockedListener2);

        console.log("Simple log.");

        Mockito.verify(mockedListener1, Mockito.times(1)).punch();
        Mockito.verify(mockedListener2, Mockito.times(1)).punch();

        console.removeListener(mockedListener1);
        console.removeListener(mockedListener2);
    }

    @Test
    public void gettingLogs() {
        console.clear();

        console.log("First");
        console.log("Second");

        List<String> l = console.getLogs();

        assert l.size() == 2;

        assert Objects.equals(l.get(0), "First");
        assert Objects.equals(l.get(1), "Second");
    }
}
