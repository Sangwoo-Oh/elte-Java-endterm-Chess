package chess.pieces;

import chess.utils.Actions;
import chess.utils.Colors;
import chess.utils.IllegalMoveException;

import java.util.*;
import java.lang.*;

public class Pawn extends Figure {
    public Pawn (Colors color, int c, int r) {
        super(color, c, r);
    }
    public void checkMove(int c, int r) throws IllegalMoveException {
        if (color == Colors.LIGHT) {
            if (r - this.r != 1 && !(r - this.r == 1 && (c - this.c == 1 || c - this.c == -1)))throw new IllegalMoveException("Not upward step");
        } else {
            if (r - this.r != -1 && !(r - this.r == -1 && (c - this.c == 1 || c - this.c == -1)))throw new IllegalMoveException("Not upward step");
        }
    }
}
