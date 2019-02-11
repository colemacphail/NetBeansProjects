package snake;

import DLibX.DConsole;
import java.awt.Color;

public class Grow {

    private DConsole dc;

    public int x;
    public int y;
    public int xPos;
    public int yPos;

    public Grow(DConsole dc, int x, int y) {
        this.dc = dc;
        this.x = x;
        this.y = y;
    }

    public void draw() {
        xPos = (x + 20) / 40;
        yPos = (y + 20) / 40;
        dc.setPaint(Color.WHITE);
        dc.fillRect(x, y, 20, 20);
    }

    public boolean pickUp(Square s, Follower f, int x, int y) {

        return this.xPos == s.xPos
                && this.yPos == s.yPos;
    }
}
