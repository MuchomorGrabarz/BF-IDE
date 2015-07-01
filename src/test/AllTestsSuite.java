package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InterpreterTest.class,
        DebuggerInterpreterTest.class,
        DebuggerTest.class,
        SimpleParserTest.class
})

public class AllTestsSuite {}
