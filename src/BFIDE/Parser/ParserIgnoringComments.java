package BFIDE.Parser;

import BFIDE.Tape.BFNode;

import java.util.List;


public abstract class ParserIgnoringComments implements Parser {

    private Parser parser;
    abstract protected String removeComments(String code);

    public ParserIgnoringComments(Parser parser) {
        this.parser = parser;
    }
    @Override
    public List<BFNode> parse(String code) {
        return parser.parse(removeComments(code));
    }
}
