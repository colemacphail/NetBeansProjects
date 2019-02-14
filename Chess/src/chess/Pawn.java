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
public class Pawn extends Piece {

    public Pawn(DConsole dc, int initX, int initY, Colour c, Board b, String s) {
        super(dc, initX, initY, c, b, s);
    }

    @Override
    public boolean canMove(int x, int y) {
        int dx = x - this.getX();
        int dy = y - this.getY();

        if (dx == 0) {
            if (this.colour == Colour.WHITE) {
                if (this.hasMoved) {
                    return dy == -1;
                } else {
                    return dy == -1 || dy == -2;
                }
            } else {
                if (this.hasMoved) {
                    return dy == 1;
                } else {
                    return dy == 1 || dy == 2;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean[][] getPossibleMoves(ArrayList<Piece> pieces) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
