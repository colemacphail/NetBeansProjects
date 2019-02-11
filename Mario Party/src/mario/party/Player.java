package mario.party;

import DLibX.DConsole;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Cole
 */
public class Player {

    private double x;
    private double y;
    private int score;
    private final Color color;
    private final DConsole dc;
    private final Game g;
    private int toMove;
    private int playerNum;
    private int curPlayerPosition;
    private ArrayList<Position> pos;

    public Player(DConsole dc, Game g, ArrayList<Position> p, Color color, int playerNum) {
        this.dc = dc;
        this.g = g;
        this.pos = p;
        this.color = color;
        this.playerNum = playerNum;
        this.curPlayerPosition = 0;
    }

    public void setSpacesToMove(int spacesToMove) {
        this.toMove = spacesToMove;
    }

    public void run() {
        this.move();
    }

    private void move() {
        for (int i = 0; i < this.toMove; i++) {

            double deltaX = pos.get(curPlayerPosition % pos.size() + 1).getX() - this.x;
            double deltaY = pos.get(curPlayerPosition % pos.size() + 1).getY() - this.y;
            double deltaD = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

            for (int q = 0; q < 100; q++) {
                this.x += Math.cos(deltaX / deltaD) / 100;
                this.y += Math.sin(deltaY / deltaD) / 100;

                this.draw();
            }
        }
    }

    private void draw() {

        dc.setOrigin(DConsole.ORIGIN_CENTER);
        this.dc.setPaint(this.color);
        this.dc.fillEllipse(this.x + g.cameraOffsetX, this.y + g.cameraOffsetY, 20, 20);
        this.dc.drawEllipse(this.x + g.cameraOffsetX, this.y + g.cameraOffsetY, 20, 20);
    }
}
