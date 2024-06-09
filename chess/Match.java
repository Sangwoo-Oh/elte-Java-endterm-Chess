
package chess;

import chess.pieces.Figure;
import chess.pieces.*;
import chess.utils.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Match {
    public HashMap<String, Figure> figures = new HashMap<>();
    public ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
    private int size;
    public Match(String filename) {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // skip header

            line = br.readLine(); // dimension
            size = Integer.parseInt(line.split(" ")[1]);

            line = br.readLine(); // skip header

            while (!(line = br.readLine()).equals("_MOVES_")) { //starting figures
                String[] token = line.split(";");
                int col = (int)token[2].charAt(0) - 96;
                int row = Integer.parseInt(String.valueOf(token[2].charAt(1)));
                if (!checkCoord(col) || !checkCoord(row)) {
                    System.out.println("Invalid input: " + line);
                    continue;
                }
                Colors color = token[0].equals("light") ? Colors.LIGHT : Colors.DARK;
                switch(token[1]) {
                    case "QUEEN":
                        figures.put(token[2], new Queen(color, col, row));break;
                    case "PAWN":
                        figures.put(token[2], new Pawn(color, col, row));break;
                    case "ROOK":
                        figures.put(token[2], new Rook(color, col, row));break;
                    case "BISHOP":
                        figures.put(token[2], new Bishop(color, col, row));break;
                    default :
                        System.out.println("Invalid input: " + line);
                        continue;
                }
            }

            while ((line = br.readLine()) != null) { // moves
                ArrayList<Integer> move = new ArrayList<>();
                int i = 1;
                for(Character c : line.toCharArray()) {
                    int num;
                    if (i % 2 != 0) {
                        num = (int)c - 96;
                    } else {
                        num = Integer.valueOf(c);
                    }
                    move.add(num);
                }
                moves.add(move);
            }

        } catch (FileNotFoundException e){
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
    }
    public void play() {
        Colors turn = Colors.LIGHT;
        for (ArrayList<Integer> move : moves) {
            String start = (char)(move.get(0) + 96) + String.valueOf(move.get(1));
            if(figures.containsKey(start)) {
                Figure figure = figures.get(start);
                if (figure.getColor() == turn) {
                    try {
                        figure.move(figures ,move.get(2), move.get(3));
                        turn = turn == Colors.LIGHT ? Colors.DARK : Colors.LIGHT;
                    } catch (IllegalMoveException e) {
                        System.out.println("The step skipped");
                    }
                } else {
                    System.out.println("The step skipped");
                }
            }
        }
    }
    public boolean checkCoord(int x) {
        return 1 <= x && x <= 5;
    }
}
