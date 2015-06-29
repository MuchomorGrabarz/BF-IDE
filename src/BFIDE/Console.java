package BFIDE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by grabarz_muchomor on 29.06.15.
 */
public class Console {

    static private Console singleton = new Console();
    static public Console getConsole() {
        return singleton;
    }

    private List<String> logs = new ArrayList<>();

    public void log(String newLog) {
        logs.add(newLog);
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    private Console() {

    }
}
