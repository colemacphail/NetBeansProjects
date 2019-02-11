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
public class Rook extends Piece{

    public Rook(DConsole dc, int initX, int initY, Colour c, Board b, String s) {
        super(dc, initX, initY, c, b, s);
    }

    @Override
    public boolean canMove(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
