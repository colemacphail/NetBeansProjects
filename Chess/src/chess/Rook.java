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
public class Rook extends Piece {

    public Rook(DConsole dc, int initX, int initY, Colour c, Board b, String s) {
        super(dc, initX, initY, c, b, s);
    }

    @Override
    public boolean canMove(int x, int y) {
        int dx = x - this.getX();
        int dy = y - this.getY();
        return dy == 0 || dx == 0;
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

        for (int k = this.x; k < this.board.getTileSet().length; k++) {//right

            if (canMoveTiles[k][this.y]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[k - 1][this.y].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[k - 1][this.y]) {
                    if (this.x != k - 1) {
                        canMoveTiles[k][this.y] = false;
                    }
                }
            }
        }
        
        for (int k = this.x; k > -1; k--) {//left

            if (canMoveTiles[k][this.y]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[k + 1][this.y].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[k + 1][this.y]) {
                    if (this.x != k + 1) {
                        canMoveTiles[k][this.y] = false;
                    }
                }
            }
        }
        
        for (int k = this.y; k < this.board.getTileSet()[0].length; k++) {//down

            if (canMoveTiles[this.x][k]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[this.x][k - 1].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[this.x][k - 1]) {
                    if (this.y != k - 1) {
                        canMoveTiles[this.x][k] = false;
                    }
                }
            }
        }
        
        for (int k = this.y; k > -1; k--) {//down

            if (canMoveTiles[this.x][k]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[this.x][k + 1].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[this.x][k + 1]) {
                    if (this.y != k + 1) {
                        canMoveTiles[this.x][k] = false;
                    }
                }
            }
        }
        
        for (int k = this.x; k > -1; k--) {//left

            if (canMoveTiles[k][this.y]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[k + 1][this.y].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[k + 1][this.y]) {
                    if (this.x != k + 1) {
                        canMoveTiles[k][this.y] = false;
                    }
                }
            }
        }
        
        for (int k = this.y; k < this.board.getTileSet()[0].length; k++) {//down

            if (canMoveTiles[this.x][k]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[this.x][k - 1].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[this.x][k - 1]) {
                    if (this.y != k - 1) {
                        canMoveTiles[this.x][k] = false;
                    }
                }
            }
        }
        
        for (int k = this.y; k > -1; k--) {//down

            if (canMoveTiles[this.x][k]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[this.x][k + 1].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[this.x][k + 1]) {
                    if (this.y != k + 1) {
                        canMoveTiles[this.x][k] = false;
                    }
                }
            }
        }
        
        for (int k = this.x; k > -1; k--) {//left

            if (canMoveTiles[k][this.y]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[k + 1][this.y].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[k + 1][this.y]) {
                    if (this.x != k + 1) {
                        canMoveTiles[k][this.y] = false;
                    }
                }
            }
        }
        
        for (int k = this.y; k < this.board.getTileSet()[0].length; k++) {//down

            if (canMoveTiles[this.x][k]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[this.x][k - 1].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[this.x][k - 1]) {
                    if (this.y != k - 1) {
                        canMoveTiles[this.x][k] = false;
                    }
                }
            }
        }
        
        for (int k = this.y; k > -1; k--) {//down

            if (canMoveTiles[this.x][k]) {

                boolean isPrevTileOccupied = this.board.getTileSet()[this.x][k + 1].getIsOccupied();
                if (isPrevTileOccupied || !canMoveTiles[this.x][k + 1]) {
                    if (this.y != k + 1) {
                        canMoveTiles[this.x][k] = false;
                    }
                }
            }
        }

        return canMoveTiles;
    }
}
