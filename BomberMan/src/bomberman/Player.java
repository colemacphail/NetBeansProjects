package bomberman;

import DLibX.DConsole;
import java.awt.*;

public class Player {

    private DConsole dc;

    //p1
    public int x1 = 75;
    public int y1 = 75;
    public int xPos1;
    public int yPos1;
    public int delay1;
    public int maxDelay1 = 30;
    public boolean hit1 = false;
    public int size1 = 1;

    //p2
    public int x2 = 875;
    public int y2 = 575;
    public int xPos2;
    public int yPos2;
    public int delay2;
    public int maxDelay2 = 30;
    public boolean hit2 = false;
    public int size2 = 1;

    //bomb variables
    public int maxBomb1 = 1;
    public int nextBomb1 = 0;

    public int maxBomb2 = 1;
    public int nextBomb2 = 0;

    public Player(DConsole dc) {
        this.dc = dc;
    }

    public void draw() {

        dc.setOrigin(DConsole.ORIGIN_CENTER);
        if (!hit1) {
            dc.setPaint(Color.DARK_GRAY);
            dc.fillRect(x1, y1, 40, 40);
            dc.setPaint(Color.RED);
            dc.fillEllipse(x1, y1, 40, 40);
        }

        if (!hit2) {
            dc.setPaint(Color.DARK_GRAY);
            dc.fillRect(x2, y2, 40, 40);
            dc.setPaint(Color.BLUE);
            dc.fillEllipse(x2, y2, 40, 40);
        }
    }

    public void move(Map m) {
        
        
        // if immediately after moving, decrease time until moving again
        if (delay1 < 30) {
            delay1++;
        }
        if (delay2 < 30) {
            delay2++;
        }

        //grid based movement
        //p1
        if (!m.block1
                && !hit1) {

            xPos1 = (x1 - 25) / 50 - 1;
            yPos1 = (y1 - 25) / 50 - 1;
            m.grid[yPos1 + 1][xPos1 + 1] = BrickID.PLAYER1;

            if (delay1 >= maxDelay1) {
                if (dc.isKeyPressed('w')) { // move up
                    m.grid[yPos1 + 1][xPos1 + 1] = BrickID.NONE;//after moving, reset to nothing
                    y1 = y1 - 50;
                    delay1 = 0;
                    if (m.grid[yPos1][xPos1 + 1] == BrickID.P_SIZE){
                        size1++;
                    }
                    if (m.grid[yPos1][xPos1 + 1] == BrickID.P_MAXBOMBS){
                        maxBomb1++;
                    }

                } else if (dc.isKeyPressed('d')) { // move right
                    m.grid[yPos1 + 1][xPos1 + 1] = BrickID.NONE;//after moving, reset to nothing
                    x1 = x1 + 50;
                    delay1 = 0;
                    if (m.grid[yPos1 + 1][xPos1 + 2] == BrickID.P_SIZE){
                        size1++;
                    }
                    if (m.grid[yPos1 + 1][xPos1 + 2] == BrickID.P_MAXBOMBS){
                        maxBomb1++;
                    }

                } else if (dc.isKeyPressed('s')) { // move down
                    m.grid[yPos1 + 1][xPos1 + 1] = BrickID.NONE;//after moving, reset to nothing
                    y1 = y1 + 50;
                    delay1 = 0;
                    if (m.grid[yPos1 + 2][xPos1 + 1] == BrickID.P_SIZE){
                        size1++;
                    }
                    if (m.grid[yPos1 + 2][xPos1 + 1] == BrickID.P_MAXBOMBS){
                        maxBomb1++;
                    }

                } else if (dc.isKeyPressed('a')) { // move left
                    m.grid[yPos1 + 1][xPos1 + 1] = BrickID.NONE;//after moving, reset to nothing
                    x1 = x1 - 50;
                    delay1 = 0;
                    if (m.grid[yPos1 + 1][xPos1] == BrickID.P_SIZE){
                        size1++;
                    }
                    if (m.grid[yPos1 + 1][xPos1] == BrickID.P_MAXBOMBS){
                        maxBomb1++;
                    }

                }
            }
        }

        if (hit1) {
            xPos1 = 0;
            yPos1 = 0;
        }

        //p2
        if (!m.block2
                && !hit2) {

            xPos2 = (x2 - 25) / 50 - 1;
            yPos2 = (y2 - 25) / 50 - 1;
            m.grid[yPos2 + 1][xPos2 + 1] = BrickID.PLAYER2;

            if (delay2 >= maxDelay2) {

                //arrow keys to move
                if (dc.isKeyPressed(38)) { // move up
                    m.grid[yPos2 + 1][xPos2 + 1] = BrickID.NONE;//after moving, reset to nothing
                    y2 = y2 - 50;
                    delay2 = 0;
                    if (m.grid[yPos2][xPos2 + 1] == BrickID.P_SIZE){
                        size2++;
                    }
                    if (m.grid[yPos2][xPos2 + 1] == BrickID.P_MAXBOMBS){
                        maxBomb2++;
                    }

                } else if (dc.isKeyPressed(39)) { // move right
                    m.grid[yPos2 + 1][xPos2 + 1] = BrickID.NONE;//after moving, reset to nothing
                    x2 = x2 + 50;
                    delay2 = 0;
                    if (m.grid[yPos2 + 1][xPos2 + 2] == BrickID.P_SIZE){
                        size2++;
                    }
                    if (m.grid[yPos2 + 1][xPos2 + 2] == BrickID.P_MAXBOMBS){
                        maxBomb2++;
                    }

                } else if (dc.isKeyPressed(40)) { // move down
                    m.grid[yPos2 + 1][xPos2 + 1] = BrickID.NONE;//after moving, reset to nothing
                    y2 = y2 + 50;
                    delay2 = 0;
                    if (m.grid[yPos2 + 2][xPos2 + 1] == BrickID.P_SIZE){
                        size2++;
                    }
                    if (m.grid[yPos2 + 2][xPos2 + 1] == BrickID.P_MAXBOMBS){
                        maxBomb2++;
                    }

                } else if (dc.isKeyPressed(37)) { // move left
                    m.grid[yPos2 + 1][xPos2 + 1] = BrickID.NONE;//after moving, reset to nothing
                    x2 = x2 - 50;
                    delay2 = 0;
                    if (m.grid[yPos2 + 1][xPos2] == BrickID.P_SIZE){
                        size2++;
                    }
                    if (m.grid[yPos2 + 1][xPos2] == BrickID.P_MAXBOMBS){
                        maxBomb2++;
                    }
                }

            }
        }

        if (hit2) {
            xPos2 = 0;
            yPos2 = 0;
        }
    }
}
