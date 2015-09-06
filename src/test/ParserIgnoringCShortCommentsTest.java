package test;

import BFIDE.Parser.ParserIgnoringCShortComments;
import BFIDE.Parser.SimpleParser;
import org.junit.Before;

public class ParserIgnoringCShortCommentsTest extends ParsersIgnoringCommentsTest {
    @Before
    public void setCommentMarkAndParser() {
        commentMark = "//";
        parser = new ParserIgnoringCShortComments(new SimpleParser());
    }
}
