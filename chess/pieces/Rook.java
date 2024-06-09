package chess.pieces;

import chess.utils.Actions;
import chess.utils.Colors;
import chess.utils.IllegalMoveException;

import java.util.*;
import java.lang.*;

public class Rook extends Figure {
    public Rook (Colors color, int c, int r) {
        super(color, c, r);
    }
    public void checkMove(int c, int r) throws IllegalMoveException {
        if (this.c != 0 && this.r != 0) throw new IllegalMoveException("Not horizontal or vertical step");
    }
}
