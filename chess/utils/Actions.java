package chess.utils;

import chess.pieces.Figure;
import chess.utils.Actions;
import chess.utils.Colors;
import chess.utils.IllegalMoveException;

import java.util.*;
import java.lang.*;

public interface Actions {
    public void checkMove(int c, int r) throws IllegalMoveException;
    public void checkPath(HashMap<String, Figure> map, int c, int r) throws IllegalMoveException;
    public Figure checkField(HashMap<String, Figure> map, int c, int r) throws IllegalMoveException;
    public int move(HashMap<String, Figure> map, int c, int r) throws IllegalMoveException ;
}
