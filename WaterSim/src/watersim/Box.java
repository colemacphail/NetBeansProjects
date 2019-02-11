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
public class Box extends PhysicsObject{

    private int width;
    private int height;
    
    public Box(DConsole dc, Coordinate p, Color c, int density) {
        super(dc, p, c, density);
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
