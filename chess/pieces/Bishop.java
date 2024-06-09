package chess.pieces;

import chess.utils.Actions;
import chess.utils.Colors;
import chess.utils.IllegalMoveException;

import java.util.*;
import java.lang.*;

public class Bishop extends Figure {
    public Bishop (Colors color, int c, int r) {
        super(color, c, r);
    }
    public void checkMove(int c, int r) throws IllegalMoveException {
        if (Math.abs(this.c - c) != Math.abs(this.r - r))
            throw new IllegalMoveException("Not diagonal step");
    }
}

