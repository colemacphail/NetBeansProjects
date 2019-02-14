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
public class Bishop extends Piece {

    public Bishop(DConsole dc, int initX, int initY, Colour c, Board b, String s) {
        super(dc, initX, initY, c, b, s);
    }

    @Override
    public boolean canMove(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;

        return Math.abs(dx) == Math.abs(dy);
    }

    @Override
    public boolean[][] getPossibleMoves(ArrayList<Piece> pieces) {

        boolean[][] canMoveTiles = new boolean[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];

        for (int j = 0; j < canMoveTiles.length; j++) {
            for (int k = 0; k < canMoveTiles[0].length; k++) {
                boolean isValid = true;

                if (!this.canMove(j, k)) { // if the piece cannot move to a tile, it is not valid
                    isValid = false;
                }
                for (int i = 0; i < pieces.size(); i++) { // if a tile is occupied, it is not valid
                    if (pieces.get(i).getX() == j
                            && pieces.get(i).getY() == k
                            && getIsSameColour(pieces.get(i))) {
                        isValid = false;
                    }
                }

                canMoveTiles[j][k] = isValid;
            }
        }

        for (int j = 0; j < canMoveTiles.length; j++) {
            for (int k = 0; k < canMoveTiles[0].length; k++) {

                if (canMoveTiles[j][k]) {

                    int dx = j - this.x;
                    int dy = k - this.y;

                    if (dx > 0 && dy > 0) {
                        boolean isPrevTileOccupied = this.board.getTileSet()[this.x + dx - 1][this.y + dy - 1].getIsOccupied();
                        if (isPrevTileOccupied || !canMoveTiles[j - 1][k - 1]) {
                            canMoveTiles[j][k] = false;
                        }
                    } else if (dx < 0 && dy > 0) {
                        boolean isPrevTileOccupied = this.board.getTileSet()[this.x + dx + 1][this.y + dy - 1].getIsOccupied();
                        if (isPrevTileOccupied || !canMoveTiles[j + 1][k - 1]) {
                            canMoveTiles[j][k] = false;
                        }
                    } else if (dx > 0 && dy < 0) {
                        boolean isPrevTileOccupied = this.board.getTileSet()[this.x + dx - 1][this.y + dy + 1].getIsOccupied();
                        if (isPrevTileOccupied || !canMoveTiles[j - 1][k + 1]) {
                            canMoveTiles[j][k] = false;
                        }
                    } else if (dx < 0 && dy < 0) {
                        boolean isPrevTileOccupied = this.board.getTileSet()[this.x + dx + 1][this.y + dy + 1].getIsOccupied();
                        if (isPrevTileOccupied || !canMoveTiles[j + 1][k + 1]) {
                            canMoveTiles[j][k] = false;
                        }
                    }

                }

            }
        }
        return canMoveTiles;

    }
}
