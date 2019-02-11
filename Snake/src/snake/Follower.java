package snake;

import DLibX.DConsole;
import java.awt.*;

public class Follower {

    private DConsole dc;

    public int x;
    public int y;
    public int xPos;
    public int yPos;
    public int prevX;
    public int prevY;

    public Follower(DConsole dc, int x, int y) {
        this.dc = dc;
        this.x = x;
        this.y = y;
    }

    public void draw() {
        this.xPos = (x + 20) / 40;
        this.yPos = (y + 20) / 40;
        dc.setPaint(Color.GREEN);
        dc.fillRect(x, y, 40, 40);
        dc.setPaint(Color.BLACK);
        dc.drawRect(x, y, 40, 40);
    }
}
