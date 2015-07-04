package test;

import BFIDE.Tape;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InterpreterTest.class,
        DebuggerInterpreterTest.class,
        DebuggerTest.class,
        SimpleParserTest.class,
        Tape.class
})

public class AllTestsSuite {}
