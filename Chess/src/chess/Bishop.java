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

    public Bishop(DConsole dc, int initX, int initY, Colour c, Board b, String s, int id) {
        super(dc, initX, initY, c, b, s, id);
    }

    @Override
    public boolean canMove(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;

        return Math.abs(dx) == Math.abs(dy);
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
                            && getIsSameColour(this.board.getPieceList().get(i))) {
                        isValid = false;
                    }
                }

                canMoveTiles[j][k] = isValid;
            }
        }

        for (int j = this.x; j < this.board.getTileSet().length; j++) {//right
            for (int k = this.y; k < this.board.getTileSet()[0].length; k++) {//down

                if (canMoveTiles[j][k]) {

                    boolean isPrevTileOccupied = this.board.getTileSet()[j - 1][k - 1].getIsOccupied();
                    if (isPrevTileOccupied || !canMoveTiles[j - 1][k - 1]) {
                        if (this.x != j - 1 && this.y != k - 1) {
                            canMoveTiles[j][k] = false;
                        }
                    }
                }
            }
        }
        for (int j = this.x; j < this.board.getTileSet().length; j++) {//right
            for (int k = this.y; k > -1; k--) {//up

                if (canMoveTiles[j][k]) {

                    boolean isPrevTileOccupied = this.board.getTileSet()[j - 1][k + 1].getIsOccupied();
                    if (isPrevTileOccupied || !canMoveTiles[j - 1][k + 1]) {
                        if (this.x != j - 1 && this.y != k + 1) {
                            canMoveTiles[j][k] = false;
                        }
                    }
                }
            }
        }
        for (int j = this.x; j > -1; j--) {//left
            for (int k = this.y; k < this.board.getTileSet()[0].length; k++) {//down

                if (canMoveTiles[j][k]) {

                    boolean isPrevTileOccupied = this.board.getTileSet()[j + 1][k - 1].getIsOccupied();
                    if (isPrevTileOccupied || !canMoveTiles[j + 1][k - 1]) {
                        if (this.x != j + 1 && this.y != k - 1) {
                            canMoveTiles[j][k] = false;
                        }
                    }
                }
            }
        }
        for (int j = this.x; j > -1; j--) {//left
            for (int k = this.y; k > -1; k--) {//up

                if (canMoveTiles[j][k]) {

                    boolean isPrevTileOccupied = this.board.getTileSet()[j + 1][k + 1].getIsOccupied();
                    if (isPrevTileOccupied || !canMoveTiles[j + 1][k + 1]) {
                        if (this.x != j + 1 && this.y != k + 1) {
                            canMoveTiles[j][k] = false;
                        }
                    }
                }
            }
        }

        return canMoveTiles;
    }
    
    @Override
    public Piece clone() {
        return new Bishop(this.dc, this.x, this.y, this.colour, this.board, this.sprite, this.id);
    }
}
