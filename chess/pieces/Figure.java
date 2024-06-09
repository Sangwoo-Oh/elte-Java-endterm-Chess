package chess.pieces;

import chess.utils.Actions;
import chess.utils.Colors;
import chess.utils.IllegalMoveException;

import java.util.*;
import java.lang.*;

public abstract class Figure implements Actions {
    protected final Colors color;
    public final Colors getColor() {
        return color;
    }
    protected int c = 0;
    protected int r = 0;
    public void setPos(int c, int r) {
        this.c = c;
        this.r = r;
    }
    public int[] getPos() {
        return new int[]{c,r};
    }
    public Figure(Colors color, int c, int r) {
        this.color = color;
        this.c = c;
        this.r = r;
    }
    public String fieldName(int c, int r) {
        return (char)((int)c + 96) + String.valueOf(r);
    }

    private String fieldName() {
        return fieldName(c, r);
    }

    public abstract void checkMove(int c, int r) throws IllegalMoveException;

    public void checkPath(HashMap<String, Figure> map, int c, int r) throws IllegalMoveException {
        int currentC = this.c;
        int currentR = this.c;

        while (currentC != c || currentR != r) {
            if (currentC - c > 0) {
                currentC--;
            } else if (currentC - c < 0) {
                currentC++;
            }
            if (currentR - r > 0) {
                currentR--;
            } else if (currentR - r < 0) {
                currentR++;
            }
            if ((currentC != c || currentR != r) && map.containsKey(fieldName(currentC, currentR))) {
                throw new IllegalMoveException("There is " + map.get(fieldName(currentC, currentR)) + " at " + fieldName(currentC, currentR));
            }
        }
    }
    public Figure checkField(HashMap<String, Figure> map, int c, int r) {
        if (map.containsKey(fieldName(c,r))) {
            return map.get(fieldName(c,r));
        } return null;

    }
    public int move(HashMap<String, Figure> map, int c, int r) throws IllegalMoveException {
        if (this.c == 0 && this.r == 0) return 0;

        try {
            checkMove(c,r);
            checkPath(map, c, r);

            Colors opponent = color == Colors.LIGHT ? Colors.DARK : Colors.LIGHT;

            if (checkField(map, c, r) == null) {
                if (this instanceof Pawn && c - this.c != 0) {
                    return 0;
                } else {
                    map.put(fieldName(c,r), this);
                    setPos(c,r);
                    return 1;
                }
            }

            if (checkField(map, c, r).color == opponent) {
                if (this instanceof Pawn && c - this.c == 0) {
                    return 0;
                } else {
                    Figure prev = map.get(fieldName(c,r));
                    map.remove(fieldName(c,r));
                    prev.setPos(0,0);
                    map.put(fieldName(c,r), this);
                    setPos(c,r);
                    return 1;
                }
            }

            return 0;
        } catch (IllegalMoveException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return color + " " + this.getClass().getSimpleName() + " " + fieldName(c,r) ;
    }
}
