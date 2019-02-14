/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import DLibX.DConsole;
import java.awt.Color;

/**
 *
 * @author Cole
 */
public class Tile implements Drawable {

    private final DConsole dc;
    private final int x;
    private final int y;
    private final Color colour;
    private final int size = Constants.TILE_SIZE;
    private boolean isSelected = false;
    private boolean isHighlighted = false;
    private boolean isOccupied = false;

    public Tile(DConsole dc, int x, int y, Color colour) {
        this.dc = dc;
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    @Override
    public void draw() {
        if (this.isSelected || this.isHighlighted) {
            this.dc.setPaint(new Color(255, 128, 114, 255));
        } else {
            this.dc.setPaint(this.colour);
        }
        this.dc.fillRect(this.x, this.y, this.size, this.size);
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSize() {
        return this.size;
    }

    public void setHighlighted(boolean isHighlighted) {
        this.isHighlighted = isHighlighted;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    
    public boolean getIsOccupied(){
        return this.isOccupied;
    }
}
