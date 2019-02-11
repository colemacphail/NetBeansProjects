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
public class Water extends Liquid{
    
    public Water(DConsole dc, Coordinate p) {
        super(dc, p, Color.BLUE, 1000);
    }
    
}
