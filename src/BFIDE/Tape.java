package BFIDE;

import java.util.List;

public class Tape<T> {

    List<T> nodes;
    List<Listener> listeners;
    private int pos;

    public int getLength() {
        return nodes.size();
    }

    public void reset(List<T> initList) {
        pos = 0;
        nodes = initList;
    }

    public int getPosition() {
        return pos;
    }
    public void setPosition(int pos) {
        this.pos = pos;
        listeners.forEach(BFIDE.Listener::punch);
    }

    public void registerListener(Listener listener) {
        listeners.add(listener);
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public T getValue() {
        return nodes.get(pos);
    }
    public void setValue(T val) {
        nodes.set(pos,val);
        listeners.forEach(BFIDE.Listener::punch);
    }

    public List<T> getPiece(int position, int i) {
        return nodes.subList(position,i);
    }
}
