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
public abstract class Piece {

    private final DConsole dc;
    private int x;
    private int y;
    private final Colour colour;
    private String sprite;
    private Board board;
    boolean isSelected = false;

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

    public void draw() {
        try{
        dc.drawImage(this.sprite, this.x * 80 + 10, this.y * 80 + 10);
        } catch(Exception e){
            System.out.println(e + "at: " + sprite);
        }
    }

    public Colour getColour() {
        return this.colour;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
    
    public boolean getIsSelected(){
        return this.isSelected;
    }

    public abstract ArrayList<Tile> getPossibleMoves();
    
    public abstract boolean canMove(int x, int y);

}
