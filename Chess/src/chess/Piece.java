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
    protected int initX;
    protected int initY;
    protected final Colour colour;
    protected String sprite;
    protected Board board;
    protected boolean isSelected = false;
    protected boolean prevHasMoved = false;
    protected boolean hasMoved = false;
    protected int prevX;
    protected int prevY;

    public Piece(DConsole dc, int initX, int initY, Colour c, Board b, String sprite) {
        this.dc = dc;
        this.x = initX;
        this.prevX = x;
        this.initX = initX;
        this.y = initY;
        this.prevY = y;
        this.initY = initY;
        this.colour = c;
        this.board = b;
        this.sprite = sprite;
    }

    public void move(int x, int y) {
        this.prevX = x;
        this.prevY = y;
        this.x = x;
        this.y = y;
        this.prevHasMoved = hasMoved;
        this.hasMoved = true;
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

    public abstract boolean[][] getPossibleMoves();

    public abstract boolean canMove(int x, int y);

}
