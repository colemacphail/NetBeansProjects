package bomberman;

import DLibX.DConsole;

enum BrickID { // define everything that can exist on the grid
    NONE, UNBREAKABLE, BREAKABLE, PLAYER1, PLAYER2, BOMB, BROKEN, EXPLODING,
    P_SIZE, P_MAXBOMBS, TRIGGER
}

public class BomberMan {

    public static void main(String[] args) {

        DConsole dc = new DConsole();
        Bomb b[][] = new Bomb[15][21];
        Map m = new Map(dc);
        Player p = new Player(dc);

        // increased size for canvas to allow even grid numbers (50 px squares)
        dc.setMinimumSize(955, 680);

        // can't resize
        dc.setResizable(false);

        //put game below:
        // set grid for map
        m.set();

        for (int row = 0; row < m.grid.length; row++) {
            for (int col = 0; col < m.grid[0].length; col++) {

                b[row][col] = new Bomb(dc);//here's a bomb

                b[row][col].x = col * 50 + 25;//set grid
                b[row][col].y = row * 50 + 25;

                b[row][col].xPos = (b[row][col].x - 25) / 50 - 1; //assign all possible positions to x and y values
                b[row][col].yPos = (b[row][col].y - 25) / 50 - 1;
            }
        }

        while (true) {

            dc.clear();

            //draw everything
            m.draw();//map
            p.draw();//player

            //bomb commands
            for (int row = 0; row < m.grid.length; row++) {
                for (int col = 0; col < m.grid[0].length; col++) {

                    b[row][col].check(p, m);//bomb

                    if (b[row][col].isPlaced) { //if the bomb exists, draw it, and start the timer
                        b[row][col].draw(m, p);
                    }
                }
            }

            m.check(p); //check to see if path is blocked

            p.move(m);//if it isn't, move

            dc.redraw();

            DConsole.pause(10);
        }
    }
}
