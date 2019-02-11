package topdownshooter;

import DLibX.DConsole;
import java.awt.Color;

public class Enemy {

    private DConsole dc;

    double x;
    double y;
//    double centerX;
//    double centerY;
    double health = 10;
    double angle;
    double speed;
    double width;
    double height;
    int damage;
    Color color;

    public Enemy(DConsole dc, int x, int y, double baseHealth) { // create a standard enemy
        this.dc = dc;
        this.x = x;
        this.y = y;
        this.health = baseHealth;
        this.width = 64;
        this.height = 64;
        this.speed = baseHealth / 16;
        this.damage = 1;
//        Color is based on speed, speed is based on health, health is based on wave
        if (this.speed > 3.5) {
            this.speed = 3.5;
        }
        if (this.speed < 1.5) {
            this.color = Color.RED;
        }
        if (this.speed >= 1.5) {
            this.color = Color.BLUE;
        }
        if (this.speed >= 2) {
            this.color = Color.GREEN;
        }
        if (this.speed >= 2.5) {
            this.color = Color.YELLOW;
        }
        if (this.speed >= 3) {
            this.color = Color.ORANGE;
        }
        if (this.speed >= 3.5) {
            this.color = Color.MAGENTA;
        }

    }

    public Enemy(DConsole dc, int x, int y, int w, int h, double s, int b, int d, Color c) { // create a boss

        this.dc = dc;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.speed = s;
        this.health = b;
        this.damage = d;
        this.color = c;

    }

    public void draw() {
        dc.setOrigin(DConsole.ORIGIN_CENTER);
        dc.setPaint(this.color);
        dc.fillEllipse(x, y, width, height);
        dc.setPaint(Color.BLACK);
        dc.drawEllipse(x, y, width, height);//black outline to prevent the enemies from becoming a blob
    }

    public void move(Player p) {

//        centerX = x + width / 2;
//        centerY = y + height / 2;
        double xChange = 0;
        double yChange = 0;

        //set angle based on position relative to player
        this.angle = Math.atan((p.centerY  - y) / (p.centerX  - x));

        //constantly move towards player based on angle
        if (p.centerX >= x) {
            xChange = speed * Math.cos((this.angle));
            yChange = speed * Math.sin((this.angle));
        } else if (p.centerX < x) {
            xChange = -speed * Math.cos((this.angle));
            yChange = -speed * Math.sin((this.angle));
        }

        x += xChange;
        y += yChange;

    }

    public void hit(Player p) {
        for (int i = p.b.size() - 1; i > 0; i--) {
            double bX = p.b.get(i).centerX;
            double bY = p.b.get(i).centerY;

            double xDif = bX - x;
            double yDif = bY - y;

            double distance = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));
            
            //if hit, destroy the bullet that hit the enemy, then reduce enemy health by shot damage
            if (distance < width / 2 + p.b.get(i).width / 2) {
                p.b.remove(i);
                health -= p.b.get(i - 1).damage;
            }
        }
    }
}
