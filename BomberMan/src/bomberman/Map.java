package bomberman;

import DLibX.DConsole;
import java.awt.*;

public class Map {

    private DConsole dc;

    public BrickID[][] grid = new BrickID[15][21];
    private int y;
    private int x;
    public boolean block1;
    public boolean block2;
    public int duration = 0;
    public int powerUp;

    public Map(DConsole dc) {
        this.dc = dc;
    }

    public void set() {

        for (int row = 0; row < grid.length; row++) { //bricks across
            for (int col = 0; col < grid[0].length; col++) { //bricks up and down

                grid[row][col] = BrickID.BREAKABLE; //fill all breakable walls

                // outer walls
                grid[0][col] = BrickID.UNBREAKABLE;
                grid[row][0] = BrickID.UNBREAKABLE;
                grid[12][col] = BrickID.UNBREAKABLE;
                grid[row][18] = BrickID.UNBREAKABLE;
            }
        }
        for (int row = 0; row < grid.length; row += 2) { //horizontal bricks
            for (int col = 0; col < grid[0].length; col += 2) { //vertical bricks

                grid[row][col] = BrickID.UNBREAKABLE; //black pillars

            }
        }

        //set starting corners
        grid[1][1] = BrickID.NONE;
        grid[1][2] = BrickID.NONE;
        grid[2][1] = BrickID.NONE;

        grid[11][16] = BrickID.NONE;
        grid[11][17] = BrickID.NONE;
        grid[10][17] = BrickID.NONE;

    }

    public void draw() {

        for (int row = 0; row < grid.length; row++) { // down
            for (int col = 0; col < grid[0].length; col++) { // across

                //grid coordinates
                y = row * 50 + 25;
                x = col * 50 + 25;

                if (grid[row][col] == BrickID.UNBREAKABLE) { //if indestructible

                    dc.setPaint(Color.BLACK);

                }

                if (grid[row][col] == BrickID.BREAKABLE) { // if destructible

                    dc.setPaint(Color.GRAY);

                }

                if (grid[row][col] == BrickID.EXPLODING
                        || grid[row][col] == BrickID.BROKEN) {// if the explosion animation is running

                    dc.setPaint(Color.RED);

                    if (duration <= 0) {
                        if (grid[row][col] == BrickID.EXPLODING) {
                            grid[row][col] = BrickID.NONE;
                        }

                        if (grid[row][col] == BrickID.BROKEN) {
                            powerUp = (int) Math.ceil(Math.random() * 2);

                            if (powerUp == 1) {
                                grid[row][col] = BrickID.P_MAXBOMBS;
                            }
                            if (powerUp == 2) {
                                grid[row][col] = BrickID.P_SIZE;
                            }

                        }

                    }
                }

                if (grid[row][col] == BrickID.P_MAXBOMBS) {

                    dc.drawImage("MaxBombUp.png", x, y);//draw it

                }
                if (grid[row][col] == BrickID.P_SIZE) {

                    dc.drawImage("SizeUp.png", x, y);//draw it

                }

                if (grid[row][col] != BrickID.P_SIZE
                        && grid[row][col] != BrickID.P_MAXBOMBS) {
                    dc.fillRect(x, y, 50, 50); //draw block in selected colour

                    dc.setPaint(Color.WHITE); // outside border

                    dc.drawRect(x, y, 50, 50);
                }
            }
        }
    }

//checks player collision with walls / powerups
    public void check(Player p) {

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

                //wall collision
                //p1
                if (grid[p.yPos1 + 1][p.xPos1 + 2] != BrickID.NONE
                        && grid[p.yPos1 + 1][p.xPos1 + 2] != BrickID.P_MAXBOMBS
                        && grid[p.yPos1 + 1][p.xPos1 + 2] != BrickID.P_SIZE//if you try to go right and position is blocked, don't go there
                        && dc.isKeyPressed('d')) {
                    block1 = true;
                } else if (grid[p.yPos1 + 1][p.xPos1] != BrickID.NONE
                        && grid[p.yPos1 + 1][p.xPos1] != BrickID.P_MAXBOMBS
                        && grid[p.yPos1 + 1][p.xPos1] != BrickID.P_SIZE//if you try to go left and...
                        && dc.isKeyPressed('a')) {
                    block1 = true;
                } else if (grid[p.yPos1][p.xPos1 + 1] != BrickID.NONE
                        && grid[p.yPos1][p.xPos1 + 1] != BrickID.P_MAXBOMBS
                        && grid[p.yPos1][p.xPos1 + 1] != BrickID.P_SIZE//if you try to go up and...
                        && dc.isKeyPressed('w')) {
                    block1 = true;
                } else if (grid[p.yPos1 + 2][p.xPos1 + 1] != BrickID.NONE
                        && grid[p.yPos1 + 2][p.xPos1 + 1] != BrickID.P_MAXBOMBS
                        && grid[p.yPos1 + 2][p.xPos1 + 1] != BrickID.P_SIZE//if you try to go down and...
                        && dc.isKeyPressed('s')) {
                    block1 = true;
                } else { //if the position isn't blocked, go to the position you're trying to get to
                    block1 = false;
                }
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

                //p2
                if (grid[p.yPos2 + 1][p.xPos2 + 2] != BrickID.NONE
                        && grid[p.yPos2 + 1][p.xPos2 + 2] != BrickID.P_MAXBOMBS
                        && grid[p.yPos2 + 1][p.xPos2 + 2] != BrickID.P_SIZE//if you try to go right and position is blocked, don't go there
                        && dc.isKeyPressed(39)) {
                    block2 = true;
                } else if (grid[p.yPos2 + 1][p.xPos2] != BrickID.NONE
                        && grid[p.yPos2 + 1][p.xPos2] != BrickID.P_MAXBOMBS
                        && grid[p.yPos2 + 1][p.xPos2] != BrickID.P_SIZE//if you try to go left and...
                        && dc.isKeyPressed(37)) {
                    block2 = true;
                } else if (grid[p.yPos2][p.xPos2 + 1] != BrickID.NONE
                        && grid[p.yPos2][p.xPos2 + 1] != BrickID.P_MAXBOMBS
                        && grid[p.yPos2][p.xPos2 + 1] != BrickID.P_SIZE//if you try to go up and...
                        && dc.isKeyPressed(38)) {
                    block2 = true;
                } else if (grid[p.yPos2 + 2][p.xPos2 + 1] != BrickID.NONE
                        && grid[p.yPos2 + 2][p.xPos2 + 1] != BrickID.P_MAXBOMBS
                        && grid[p.yPos2 + 2][p.xPos2 + 1] != BrickID.P_SIZE//if you try to go down and...
                        && dc.isKeyPressed(40)) {
                    block2 = true;
                } else { //if the position isn't blocked, go to the position you're trying to get to
                    block2 = false;
                }
            }
        }
    }
}
