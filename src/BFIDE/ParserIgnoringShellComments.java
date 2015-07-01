package BFIDE;

public class ParserIgnoringShellComments extends ParserIgnoringComments {
    public ParserIgnoringShellComments(Parser parser) {
        super(parser);
    }

    @Override
    protected String removeComments(String code) {
        StringBuilder builder = new StringBuilder();

        int removal = 0;

        for (Character c : code.toCharArray())
            if (removal == 1) {
                if (c == '\n') removal = 0;
            } else {
                if (c == '#') removal++;
                else removal = 0;

                builder.append(c);
            }

        return builder.toString();
    }
}
