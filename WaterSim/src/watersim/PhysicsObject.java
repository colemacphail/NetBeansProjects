/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watersim;

import DLibX.DConsole;
import java.awt.Color;

/**
 *
 * @author Cole
 */
public abstract class PhysicsObject {
    protected Color c;
    protected DConsole dc;
    protected Coordinate pos;
    protected int density;//should be in g/L
    
    public PhysicsObject(DConsole dc, Coordinate pos, Color c, int d){
        this.dc = dc;
        this.density = d;
        this.pos = pos;
        this.c = c;
    }
    
    public void fall(){
        this.pos.changeY(Constants.grav);
    }
    
    public abstract void draw();
}
