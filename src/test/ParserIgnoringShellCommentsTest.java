package test;

import BFIDE.Parser.ParserIgnoringShellComments;
import BFIDE.Parser.SimpleParser;
import org.junit.Before;

public class ParserIgnoringShellCommentsTest extends ParsersIgnoringCommentsTest {
    @Before
    public void setCommentMarkAndParser() {
        commentMark = "#";
        parser = new ParserIgnoringShellComments(new SimpleParser());
    }
}
