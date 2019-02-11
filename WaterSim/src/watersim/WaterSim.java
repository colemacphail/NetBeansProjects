/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watersim;

import DLibX.DConsole;

/**
 *
 * @author Cole
 */
public class WaterSim {

    /**
     * @param args the command line arguments
     */
    

    
    public static void main(String[] args) {
        DConsole dc = new DConsole(900, 600);
        
        LiquidTypes[] liquids = LiquidTypes.values();
        LiquidTypes liquidIndex = liquids[0];
        Liquid currLiquid = new Water(dc, Constants.baseWaterPos);
        

        while (true) {
            dc.clear();
            
            
            switch(liquidIndex){//setting what the liquid is based on what it should be
                case WATER: 
                    currLiquid = new Water(dc, Constants.baseWaterPos);
                    break;
                case OIL:
                    currLiquid = new Oil(dc, Constants.baseWaterPos);
                    break;
            }
            
            currLiquid.draw();

            DConsole.pause(20);
            dc.redraw();
        }
    }
}
