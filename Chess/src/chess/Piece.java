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
public abstract class Piece implements Drawable {

    protected final DConsole dc;
    protected int x;
    protected int y;
    protected final Colour colour;
    protected String sprite;
    protected Board board;
    protected boolean isSelected = false;

    public Piece(DConsole dc, int initX, int initY, Colour c, Board b, String sprite) {
        this.dc = dc;
        this.x = initX;
        this.y = initY;
        this.colour = c;
        this.board = b;
        this.sprite = sprite;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        try {
            dc.drawImage(this.sprite, this.x * 80 + 10, this.y * 80 + 10);
        } catch (Exception e) {
            System.out.println(e + "at: " + sprite);
        }
    }

    public Colour getColour() {
        return this.colour;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }

    public boolean getIsSameColour(Piece p) {
        return p.getColour() == this.colour;
    }

    public boolean[][] getPossibleMoves(ArrayList<Piece> pieces) {
                boolean[][] canMoveTiles = new boolean[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];

        for (int i = 0; i < pieces.size(); i++) {
            for (int j = 0; j < canMoveTiles.length; j++) {
                for (int k = 0; k < canMoveTiles[0].length; k++) {
                    if (this.canMove(j, k)) { // if the piece can move to the tile, try to do so
                        if (pieces.get(i).getX() != j // if there is no piece on that tile, that tile is available
                                && pieces.get(i).getY() != k) {
                            canMoveTiles[j][k] = true;
                        } else { // Otherwise, if the piece occupying that tile is the other colour, that tile is available

                            if (this.getIsSameColour(pieces.get(i))) {
                                canMoveTiles[j][k] = false;
                            } else {
                                canMoveTiles[j][k] = true;
                            }
                        }
                    } else {
                        canMoveTiles[j][k] = false;
                    }
                }
            }
        }

        return canMoveTiles;
    }

    public abstract boolean canMove(int x, int y);

}
