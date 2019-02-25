/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import DLibX.DConsole;
import java.util.ArrayList;

/**
 *
 * @author Cole
 */
public class Knight extends Piece {

    public Knight(DConsole dc, int initX, int initY, Colour c, Board b, String s) {
        super(dc, initX, initY, c, b, s);
    }

    @Override
    public boolean canMove(int x, int y) {
        int dx = x - this.getX();
        int dy = y - this.getY();

        return (Math.abs(dx) == 2 && Math.abs(dy) == 1) || (Math.abs(dy) == 2 && Math.abs(dx) == 1);

    }

    @Override
    public boolean[][] getPossibleMoves() {

        boolean[][] canMoveTiles = new boolean[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];

        for (int j = 0; j < canMoveTiles.length; j++) {
            for (int k = 0; k < canMoveTiles[0].length; k++) {
                boolean isValid = true;

                if (!this.canMove(j, k)) { // if the piece cannot move to a tile, it is not valid
                    isValid = false;
                }
                for (int i = 0; i < Board.PIECES.size(); i++) { // if a tile is occupied, it is not valid
                    if (Board.PIECES.get(i).getX() == j
                            && Board.PIECES.get(i).getY() == k
                            && getIsSameColour(Board.PIECES.get(i))) {
                        isValid = false;
                    }
                }

                canMoveTiles[j][k] = isValid;
            }
        }

        return canMoveTiles;

    }
}
