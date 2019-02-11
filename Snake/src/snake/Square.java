package snake;

import DLibX.DConsole;
import java.awt.*;

public class Square {

    private DConsole dc;

    int x;
    int y;
    int xChange;
    int yChange;
    int xPos;
    int yPos;
    int prevX;
    int prevY;

    public Square(DConsole dc, int x, int y) {
        this.dc = dc;
        this.x = x;
        this.y = y;
    }

    public void draw() {
        dc.setPaint(Color.GREEN);
        dc.fillRect(x, y, 40, 40);
        dc.setPaint(Color.BLACK);
        dc.drawRect(x, y, 40, 40);
    }

    public void move() {

        if (dc.isKeyPressed('w')
                && yChange <= 0) {
            this.yChange = -40;
            this.xChange = 0;
        } else if (dc.isKeyPressed('s')
                && yChange >= 0) {
            this.yChange = 40;
            this.xChange = 0;
        } else if (dc.isKeyPressed('a')
                && xChange <= 0) {
            this.xChange = -40;
            this.yChange = 0;
        } else if (dc.isKeyPressed('d')
                && xChange >= 0) {
            this.xChange = 40;
            this.yChange = 0;
        }

        this.x += this.xChange;
        this.y += this.yChange;

        this.xPos = (x + 20) / 40;
        this.yPos = (y + 20) / 40;

    }

    public void kill() {
        
        if (this.xPos <= 0
                || this.xPos >= 33
                || this.yPos <= 0
                || this.yPos >= 19) {
            dc.pause(500);
            System.exit(0);
        }
    }

}
