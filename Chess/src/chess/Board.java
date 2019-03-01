/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import DLibX.DConsole;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Cole
 */
public class Board implements Drawable {

    private final DConsole dc;
    private final Tile tiles[][] = new Tile[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];
    private int mouseX;
    private int mouseY;

    public static final ArrayList<Piece> PIECES = new ArrayList<>();

    private Colour whoseTurn = Colour.WHITE;

    public Board(DConsole dc) {
        this.dc = dc;

        for (int i = 0; i < this.tiles.length; i++) {//create all tiles
            for (int j = 0; j < this.tiles[0].length; j++) {
                if ((i + j) % 2 == 1) {
                    this.tiles[i][j] = new Tile(this.dc, i * 80, j * 80, Color.GRAY);
                } else {
                    this.tiles[i][j] = new Tile(this.dc, i * 80, j * 80, Color.WHITE);
                }
            }
        }
        //create all the pieces
        Board.PIECES.add(new Rook(this.dc, 0, 7, Colour.WHITE, this, "White Rook.png"));
        Board.PIECES.add(new Knight(this.dc, 1, 7, Colour.WHITE, this, "White Knight.png"));
        Board.PIECES.add(new Bishop(this.dc, 2, 7, Colour.WHITE, this, "White Bishop.png"));
        Board.PIECES.add(new Queen(this.dc, 3, 7, Colour.WHITE, this, "White Queen.png"));
        Board.PIECES.add(new King(this.dc, 4, 7, Colour.WHITE, this, "White King.png"));
        Board.PIECES.add(new Bishop(this.dc, 5, 7, Colour.WHITE, this, "White Bishop.png"));
        Board.PIECES.add(new Knight(this.dc, 6, 7, Colour.WHITE, this, "White Knight.png"));
        Board.PIECES.add(new Rook(this.dc, 7, 7, Colour.WHITE, this, "White Rook.png"));

        Board.PIECES.add(new Rook(this.dc, 0, 0, Colour.BLACK, this, "Black Rook.png"));
        Board.PIECES.add(new Knight(this.dc, 1, 0, Colour.BLACK, this, "Black Knight.png"));
        Board.PIECES.add(new Bishop(this.dc, 2, 0, Colour.BLACK, this, "Black Bishop.png"));
        Board.PIECES.add(new Queen(this.dc, 3, 0, Colour.BLACK, this, "Black Queen.png"));
        Board.PIECES.add(new King(this.dc, 4, 0, Colour.BLACK, this, "Black King.png"));
        Board.PIECES.add(new Bishop(this.dc, 5, 0, Colour.BLACK, this, "Black Bishop.png"));
        Board.PIECES.add(new Knight(this.dc, 6, 0, Colour.BLACK, this, "Black Knight.png"));
        Board.PIECES.add(new Rook(this.dc, 7, 0, Colour.BLACK, this, "Black Rook.png"));

        for (int i = 0; i < 8; i++) {
            Board.PIECES.add(new Pawn(this.dc, i, 6, Colour.WHITE, this, "White Pawn.png"));
            Board.PIECES.add(new Pawn(this.dc, i, 1, Colour.BLACK, this, "Black Pawn.png"));
        }
    }

    @Override
    public void draw() {//draw everything
        this.dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                this.tiles[i][j].draw();
            }
        }

        for (int i = 0; i < PIECES.size(); i++) {
            Board.PIECES.get(i).draw();
        }
    }

    public void updateGame() {
        mouseX = this.dc.getMouseXPosition();
        mouseY = this.dc.getMouseYPosition();

        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                boolean isOccupied = false;
                for (int k = 0; k < PIECES.size(); k++) {
                    if (Board.PIECES.get(k).getX() == i
                            && Board.PIECES.get(k).getY() == j) {
                        isOccupied = true;
                    }
                }

                this.tiles[i][j].setOccupied(isOccupied);

                if (this.mouseX > this.tiles[i][j].getX()
                        && this.mouseX < this.tiles[i][j].getX() + this.tiles[i][j].getSize()
                        && this.mouseY > this.tiles[i][j].getY()
                        && this.mouseY < this.tiles[i][j].getY() + this.tiles[i][j].getSize()
                        && this.dc.getMouseButton(1)) {//if you click on a tile...

                    this.deselectAllTiles();//deselect all other tiles

                    boolean hasPieceMoved = false;

                    this.tiles[i][j].setSelected(true);//select the tile clicked
                    boolean clickedPiece = false;

                    for (int k = 0; k < Board.PIECES.size(); k++) {
                        if (Board.PIECES.get(k).getX() == i
                                && Board.PIECES.get(k).getY() == j) {//if you click on a piece 

                            if (Board.PIECES.get(k).getColour() == whoseTurn) {//if it's yours
                                this.deselectAllPieces();//deselect all pieces 
                                Board.PIECES.get(k).setSelected(true);//select the clicked piece
                                clickedPiece = true;
                            }
                        }
                    }

                    if (isPieceSelected()
                            && !clickedPiece) {//once per cycle
                        if ((i != this.selectedPiece().getX()
                                || j != this.selectedPiece().getY())
                                && this.selectedPiece().getPossibleMoves()[i][j]) {//if you're not moving it to the same tile

                            this.selectedPiece().move(i, j);//move it to the selected tile
                            hasPieceMoved = true;//declare that a piece has moved; you should deselect all pieces and end turn
                            for (int k = 0; k < Board.PIECES.size(); k++) {
                                if (Board.PIECES.get(k).getX() == i 
                                        && Board.PIECES.get(k).getY() == j 
                                        && !Board.PIECES.get(k).getIsSameColour(this.selectedPiece())) {
                                    Board.PIECES.remove(k);
                                }
                            }
                        } else {
                            this.deselectAllPieces();
                        }
                    }

                    if (hasPieceMoved) {
                        this.deselectAllPieces();//deselect all pieces and end turn
                        this.deselectAllTiles();
                        if (this.whoseTurn == Colour.WHITE) {
                            this.whoseTurn = Colour.BLACK;
                        } else {
                            this.whoseTurn = Colour.WHITE;
                        }
                    }
                }
            }
        }

        if (isPieceSelected()) {
            for (int i = 0; i < this.tiles.length; i++) {
                for (int j = 0; j < this.tiles[0].length; j++) {
                    this.tiles[i][j].setHighlighted(this.selectedPiece().getPossibleMoves()[i][j]);
                }
            }
        }

    }

    private void deselectAllTiles() {
        for (int k = 0; k < this.tiles.length; k++) {
            for (int m = 0; m < this.tiles[0].length; m++) {
                this.tiles[k][m].setSelected(false);
                this.tiles[k][m].setHighlighted(false);
            }
        }
    }

    private void deselectAllPieces() {
        for (int k = 0; k < Board.PIECES.size(); k++) {
            Board.PIECES.get(k).setSelected(false);
        }
    }

    private boolean isPieceSelected() {
        boolean isSelected = false;
        for (int k = 0; k < Board.PIECES.size(); k++) {
            if (Board.PIECES.get(k).getIsSelected()) {
                isSelected = true;
            }
        }
        return isSelected;
    }

    private Piece selectedPiece() {
        Piece p = null;
        for (int k = 0; k < Board.PIECES.size(); k++) {
            if (Board.PIECES.get(k).getIsSelected()) {
                p = Board.PIECES.get(k);
            }
        }
        return p;
    }

    public ArrayList<Piece> getPieceList() {
        return Board.PIECES;
    }

    public Tile[][] getTileSet() {
        return this.tiles;
    }
}
