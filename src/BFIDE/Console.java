package BFIDE;

import java.util.*;

/**
 * Created by grabarz_muchomor on 29.06.15.
 */
public class Console {

    static private Console singleton = new Console();
    static public Console getConsole() {
        return singleton;
    }

    private List<String> logs = new ArrayList<>();
    private Set<ConsoleListener> listeners = new HashSet<>();

    public void log(String newLog) {
        logs.add(newLog);
        for(ConsoleListener cl : listeners) {
            cl.update();
        }
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public void registerListener(ConsoleListener l) {
        listeners.add(l);
    }
    public void removeListener(ConsoleListener l) {
        listeners.remove(l);
    }

    private Console() {

    }
}
