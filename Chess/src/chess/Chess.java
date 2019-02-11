/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import DLibX.DConsole;
/**
 *
 * @author Cole
 */
public class Chess {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        DConsole dc = new DConsole(640, 640);
        Board board = new Board(dc);
        
        while (true){
            dc.clear();
            board.draw();//basically everything is done in these two
            board.updateGame();//menus may be done in something else later
            dc.redraw();
            dc.pause(20);
        }
    }
    
}
