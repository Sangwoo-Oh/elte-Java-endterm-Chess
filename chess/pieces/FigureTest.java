package chess.pieces;

import chess.utils.Actions;
import chess.utils.Colors;
import chess.utils.IllegalMoveException;
import java.util.*;
import java.lang.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;


public class FigureTest{
    @Test
    public void test_place() {
        Figure bishop1 = new Bishop(Colors.DARK, 1,1);
        Figure bishop2 = new Bishop(Colors.LIGHT, 5,1);
        Figure rook1 = new Rook(Colors.DARK, 1,3);
        Figure rook2 = new Rook(Colors.LIGHT, 5,3);
        Figure queen1 = new Queen(Colors.DARK, 1,5);
        Figure queen2 = new Queen(Colors.LIGHT, 5,5);

        assertEquals("DARK Bishop a1", bishop1.toString());
        assertEquals("LIGHT Bishop e1", bishop2.toString());
        assertEquals("DARK Rook a3", rook1.toString());
        assertEquals("LIGHT Rook e3", rook2.toString());
        assertEquals("DARK Queen a5", queen1.toString());
        assertEquals("LIGHT Queen e5", queen2.toString());
    }

    @Test
    public void test_validMove() throws IllegalMoveException {
        Figure bishop1 = new Bishop(Colors.DARK, 1,1);
        HashMap<String, Figure> field = new HashMap<>();
        bishop1.move(field ,3,3);
        assertEquals("DARK Bishop c3", bishop1.toString());
    }
    @Test
    public void test_invalidMove1() {
        assertThrows(chess.utils.IllegalMoveException.class, () -> {
            HashMap<String, Figure> field = new HashMap<>();
            Figure bishop1 = new Bishop(Colors.DARK, 1,1);
            bishop1.move(field, 3, 3);
            Figure bishop2 = new Bishop(Colors.LIGHT, 1,1);
            bishop2.move(field ,4, 4);
        });
    }
    @Test
    public void test_invalidMove2() {
        Figure bishop = new Bishop(Colors.DARK, 1,1);
        HashMap<String, Figure> field = new HashMap<>();
        assertThrows(chess.utils.IllegalMoveException.class, () -> {
            bishop.move(field ,1,3);
        });
    }
    @Test
    public void test_invalidMove3() {
        Figure rook = new Rook(Colors.DARK, 1,3);
        HashMap<String, Figure> field = new HashMap<>();
        assertThrows(chess.utils.IllegalMoveException.class, () -> {
            rook.move(field ,3,3);
        });
    }
    @Test
    public void test_invalidMove4() {
        Figure queen = new Queen(Colors.DARK, 1,5);
        HashMap<String, Figure> field = new HashMap<>();
        assertThrows(chess.utils.IllegalMoveException.class, () -> {
            queen.move(field ,2,3);
        });
    }

    @Test
    public void test_pawnMoveValid() throws IllegalMoveException  {
        Figure pawnD = new Pawn(Colors.DARK, 3,5);
        Figure pawnL = new Pawn(Colors.LIGHT, 3,1);
        HashMap<String, Figure> field = new HashMap<>();
        pawnD.move(field ,3,4);
        pawnL.move(field ,3,2);
        assertEquals("DARK Pawn c4", pawnD.toString());
        assertEquals("LIGHT Pawn c2", pawnL.toString());
    }

    @Test
    public void test_pawnMoveInvalid() {
        Figure pawnD = new Pawn(Colors.DARK, 3,3);
        HashMap<String, Figure> field = new HashMap<>();
        assertThrows(chess.utils.IllegalMoveException.class, () -> {
            pawnD.move(field ,3,4);
        });
    }

    @Test
    public void test_capture1() throws IllegalMoveException{
        Figure queen = new Queen(Colors.DARK, 3,3);
        assertArrayEquals(new int[]{3,3}, queen.getPos());
        HashMap<String, Figure> field = new HashMap<>();
        Figure bishop = new Bishop(Colors.LIGHT, 2,2);
        bishop.move(field, 4,4);
        queen.move(field, 4,4);
        assertArrayEquals(new int[]{4,4}, queen.getPos());
        assertArrayEquals(new int[]{0,0}, bishop.getPos());
    }

    @Test
    public void test_capture2() throws IllegalMoveException{
        HashMap<String, Figure> field = new HashMap<>();
        Figure bishop = new Bishop(Colors.DARK, 2,2);
        bishop.move(field, 4,4);
        Figure pawn = new Pawn(Colors.LIGHT, 3,3);
        pawn.move(field, 4,4);
        assertArrayEquals(new int[]{4,4}, pawn.getPos());
        assertArrayEquals(new int[]{0,0}, bishop.getPos());
    }

}
