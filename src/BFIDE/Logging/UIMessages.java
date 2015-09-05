package BFIDE.Logging;

public class UIMessages {
    /*
    A class which groups all UI messages for easy modification and testing.
     */
    public static final String noInput = "Insufficient input given";
    public static final String negIndexes = "Program pointer is at negative indexes";
    public static final String outOfTape = "Program pointer moved to indexes bigger than tapeView size";
    public static final String programEnded = "Program ended execution";
    public static final String illegaState = "Program is in illegal state (look on the previous logs to check why is that)";
    public static final String inputException = "Exception occured when fetching input";
    public static final String alreadyEnded = "Program ended, state needs to be cleared before next run";
}
