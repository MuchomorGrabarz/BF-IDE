package BFIDE;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Tape {

    List<BFNode> nodes;
    Set<Listener> listeners = new HashSet<>();
    private int pos;

    public int getLength() {
        return nodes.size();
    }

    public void reset(List<BFNode> initList) {
        pos = 0;
        nodes = initList;

        listeners.forEach(BFIDE.Listener::punch);
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

    public BFNode getValue() {
        return nodes.get(pos);
    }
    public void setValue(BFNode val) {
        nodes.set(pos,val);
        listeners.forEach(BFIDE.Listener::punch);
    }

    public List<BFNode> getPiece(int position, int i) {

        int begin, end;

        begin = Integer.max(position, 0);
        end = Integer.min(nodes.size(), position+i);

        List<BFNode> prefix = new LinkedList<>();
        List<BFNode> sufix = new LinkedList<>();

        for(int j = position; j<begin; j++) {
            prefix.add(new BFNode('0'));
        }
        for(int j = end; j<position+i; j++) {
            sufix.add(new BFNode('0'));
        }

        List<BFNode> result = new LinkedList<>();

        result.addAll(prefix);
        result.addAll(nodes.subList(begin,end));
        result.addAll(sufix);

        return result;
    }
}
