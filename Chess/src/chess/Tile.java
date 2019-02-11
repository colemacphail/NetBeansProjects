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
public class Tile {
    private final DConsole dc;
    private final int x;
    private final int y;
    private final Color colour;
    private final int size;
    private boolean isSelected = false;
    
    public Tile(DConsole dc, int x, int y, Color colour){
        this.dc = dc;
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.size = 80;
    }
    
    public void draw(){
        if (this.isSelected){
            this.dc.setPaint(new Color(255, 128, 114, 255));
        } else {
            this.dc.setPaint(this.colour);
        }
        this.dc.fillRect(this.x, this.y, this.size, this.size);
    }
    
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getSize(){
        return this.size;
    }
}
