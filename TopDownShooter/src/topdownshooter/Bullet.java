package topdownshooter;

import DLibX.DConsole;
import java.awt.Color;

public class Bullet {

    private DConsole dc;

    double x;
    double y;
    double centerX;
    double centerY;
    double speed;
    double angle;
    double xChange;
    double yChange;
    int width = 5;
    double damage = 0;

    public Bullet(DConsole dc, Player p, int w, double d, double s) {
        this.dc = dc;
        this.x = p.centerX;
        this.y = p.centerY;
        this.width = w;
        this.angle = p.angle - Math.PI / 2;
        this.speed = s;
        this.damage = d;
    }

    public void draw() {
        this.dc.setOrigin(DConsole.ORIGIN_CENTER);
        this.dc.setPaint(Color.BLACK);
        this.dc.fillEllipse(x, y, width, width);
    }

    public void move() {

        this.xChange = this.speed * Math.cos((this.angle));
        this.yChange = this.speed * Math.sin((this.angle));

        this.x += this.xChange;
        this.y += this.yChange;
        this.centerX = this.x + 2.5;
        this.centerY = this.y + 2.5;

    }

}
