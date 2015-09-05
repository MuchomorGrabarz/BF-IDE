package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConsoleTest.class,
        DebuggerInterpreterTest.class,
        InterpreterTest.class,
        DebuggerTest.class,
        SimpleParserTest.class,
        TapeTest.class
})

public class AllTestsSuite {}
