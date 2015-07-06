package BFIDE.Logging;

public class LoggerConsoleImplementation implements LoggerImplementation {

    public void log(String text) {
        Console.getConsole().log(text);
    }
}
