package BFIDE.Logging;

import BFIDE.Listener;

import java.util.*;

public class Console {

    static private Console singleton = new Console();
    static public Console getConsole() {
        return singleton;
    }

    private List<String> logs = new ArrayList<>();
    private Set<Listener> listeners = new HashSet<>();

    public void log(String newLog) {
        logs.add(newLog);
        listeners.forEach(BFIDE.Listener::punch);
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public void registerListener(Listener l) {
        listeners.add(l);
    }
    public void removeListener(Listener l) {
        listeners.remove(l);
    }

    public void clear() {
        logs.clear();
    }

    private Console() {

    }
}
