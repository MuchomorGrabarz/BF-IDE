package BFIDE;

/**
 * Created by michaziobro on 27.06.2015.
 */
public class Converter {

    private String plus;
    private String minus;
    private String goLeft;
    private String goRight;
    private String dot;
    private String colon;
    private String leftBracket;
    private String rightBracket;

    private String beginning;
    private String ending;

    public void setPlus(String plus) {
        this.plus = plus;
    }
    public void setMinus(String minus) {
        this.minus = minus;
    }
    public void setGoLeft(String goLeft) {
        this.goLeft = goLeft;
    }
    public void setGoRight(String goRight) {
        this.goRight = goRight;
    }
    public void setDot(String dot) {
        this.dot = dot;
    }
    public void setColon(String colon) {this.colon = colon;}
    public void setLeftBracket(String leftBracket) {
        this.leftBracket = leftBracket;
    }
    public void setRightBracket(String rightBracket) {
        this.rightBracket = rightBracket;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }
    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String convert(String input) {
        StringBuilder result = new StringBuilder();

        result.append(beginning);

        for(char c : input.toCharArray()) {
            switch (c) {
                case '+':
                    result.append(plus);
                    break;
                case '-':
                    result.append(minus);
                    break;
                case '>':
                    result.append(goRight);
                    break;
                case '<':
                    result.append(goLeft);
                    break;
                case '.':
                    result.append(dot);
                    break;
                case ',':
                    result.append(colon);
                    break;
                case '[':
                    result.append(leftBracket);
                    break;
                case ']':
                    result.append(rightBracket);
                    break;
            }
        }

        result.append(ending);
        return result.toString();
    }
}
