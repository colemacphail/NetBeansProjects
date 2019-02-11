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
public class Oil extends Liquid{
    
    public Oil(DConsole dc, Coordinate p) {
        super(dc, p, Color.yellow, 910);
    }
    
}
