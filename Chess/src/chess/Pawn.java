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
public class Pawn extends Piece {

    public Pawn(DConsole dc, int initX, int initY, Colour c, Board b, String s) {
        super(dc, initX, initY, c, b, s);
    }

    @Override
    public boolean canMove(int x, int y) {
        int dx = x - this.getX();
        int dy = y - this.getY();

        if (dx == 0) {
            if (this.colour == Colour.WHITE) {
                if (this.hasMoved) {
                    return dy == -1 && !this.board.getTileSet()[x][y].getIsOccupied();
                } else {
                    return (dy == -1 && !this.board.getTileSet()[x][y].getIsOccupied())
                            || (dy == -2 && (!this.board.getTileSet()[x][y + 1].getIsOccupied() && !this.board.getTileSet()[x][y].getIsOccupied()));
                }
            } else {
                if (this.hasMoved) {
                    return dy == 1 && !this.board.getTileSet()[x][y].getIsOccupied();
                } else {
                    return (dy == 1 && !this.board.getTileSet()[x][y].getIsOccupied())
                            || (dy == 2 && (!this.board.getTileSet()[x][y - 1].getIsOccupied() && !this.board.getTileSet()[x][y].getIsOccupied()));
                }
            }
        } else if (Math.abs(dx) == 1) {

            if (this.colour == Colour.WHITE) {
                if (dy == -1) {
                    for (int i = 0; i < this.board.getPieceList().size(); i++) {
                        if (this.board.getPieceList().get(i).getX() == x
                                && this.board.getPieceList().get(i).getY() == y
                                && !this.getIsSameColour(this.board.getPieceList().get(i))) {
                            return true;
                        }
                    }
                }
            } else {
                if (dy == 1) {
                    for (int i = 0; i < this.board.getPieceList().size(); i++) {
                        if (this.board.getPieceList().get(i).getX() == x
                                && this.board.getPieceList().get(i).getY() == y
                                && !this.getIsSameColour(this.board.getPieceList().get(i))) {
                            return true;
                        }
                    }
                }
            }

        }
        return false;
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
                for (int i = 0; i < this.board.getPieceList().size(); i++) { // if a tile is occupied, it is not valid
                    if (this.board.getPieceList().get(i).getX() == j
                            && this.board.getPieceList().get(i).getY() == k
                            && this.getIsSameColour(this.board.getPieceList().get(i))) {
                        isValid = false;
                    }
                }

                canMoveTiles[j][k] = isValid;
            }
        }

        return canMoveTiles;

    }
}
