package chess;

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

public class MatchTest {
    @ParameterizedTest
    @CsvSource({
        "a5 , DARK Rook a5",
        "c1 , LIGHT Queen c1",
    })
    public void beforeRun(String pos, String result) {
        Match m = new Match("Chess5x5.txt");
        assertEquals(result, m.figures.get(pos).toString());
    }

    @ParameterizedTest
    @CsvSource({
        "a5 , DARK Rook a5",
        "b1 , LIGHT Queen b1",
    })
    public void afterRun(String pos, String result) {
        Match m = new Match("Chess5x5.txt");
        m.play();
        assertEquals(result, m.figures.get(pos).toString());
    }
}
