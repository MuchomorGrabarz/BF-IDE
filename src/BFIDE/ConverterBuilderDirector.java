package BFIDE;

/**
 * Created by michaziobro on 27.06.2015.
 */
public class ConverterBuilderDirector {
    ConverterBuilder builder;

    public ConverterBuilderDirector(ConverterBuilder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.setBeginning();

        builder.setPlusConversion();
        builder.setMinusConversion();
        builder.setDotConversion();
        builder.setColonConversion();
        builder.setLeftBracketConversion();
        builder.setRightBracketConversion();
        builder.setGoLeftConversion();
        builder.setGoRightConversion();

        builder.setEnding();
    }

    public Converter getResult() {
        return builder.getResult();
    }
}
