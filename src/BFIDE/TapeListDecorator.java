package BFIDE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grabarz_muchomor on 29.06.15.
 */
public class TapeListDecorator extends ArrayList {
    private int actualPosition;

    public int getActualPosition() {
        return actualPosition;
    }
    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }

    List<Listener> listeners = new ArrayList<>();

    public void registerListener() {

    }
    public void removeListener() {

    }

}
