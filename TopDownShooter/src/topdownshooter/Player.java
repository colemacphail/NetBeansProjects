package topdownshooter;

import DLibX.DConsole;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.*;

public class Player {

    private DConsole dc;
    int reloadTime = 0;
    double shotDelay;
    double shotSpeed;
    int superShot = 0;
    double x;
    double y;
    double centerX;
    double centerY;
    double mouseX;
    double mouseY;
    double xChange;
    double yChange;
    double angle;
    double maxSpeed = 3;
    double damage;
    int width;
    int health = 3;
    int iFrames = 0;
    List<String> items = new ArrayList<>();
    ArrayList<Bullet> b = new ArrayList<>();

    public Player(DConsole dc, int x, int y) {
        this.shotDelay = 20;
        this.shotSpeed = 3;
        this.dc = dc;
        this.x = x;
        this.y = y;
        this.damage = 1;
        this.width = 64;
    }

    public void loadStats() {

        for (int i = 0; i < items.size(); i++) {//update all your stats
            switch (items.get(i)) {//add stats based on the content of the item list

                case "bullets":
                    this.shotDelay -= 1.00;
                    break;

                case "speed":
                    this.maxSpeed += 0.5;
                    break;

                case "damage":
                    this.damage += 0.5;
                    break;
            }
        }
    }

    public void addPowerUp(String newPowerUp) { // add the stats from the powerups
        switch (newPowerUp) { // whenever you gain a new, individual powerup, check its contents, then add stats

            case "bullets":
                this.shotDelay -= 1.00;//reduced delay between shots is good
                break;

            case "speed":
                this.maxSpeed += 0.5;
                break;

            case "damage":
                this.damage += 0.5;
                break;

            case "health":
                this.health++;
                break;
        }
    }

    public void draw() {
        this.mouseX = this.dc.getMouseXPosition() - 32;
        this.mouseY = this.dc.getMouseYPosition() - 32;

        angle = Math.atan((mouseY - y) / (mouseX - x));

        dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);

        if (mouseX >= x) {
            angle = angle + Math.PI / 2;
            dc.setTransform(AffineTransform.getRotateInstance(angle, centerX, centerY));//rotate to attack angle if appropriate
        } else {
            angle = angle - Math.PI / 2;
            dc.setTransform(AffineTransform.getRotateInstance(angle, centerX, centerY));//rotate to attack angle if appropriate
        }

        dc.setPaint(Color.BLACK);
        dc.drawImage("charSprite.png", x, y);
        dc.setTransform(AffineTransform.getRotateInstance((0), centerX, centerY));//reset angle when drawing anything else
        dc.setOrigin(DConsole.ORIGIN_CENTER);//reset origin for anything else

    }

    public void move() {

        double acceleration = maxSpeed / 6;//6 frames to get to full speed

        if (dc.isKeyPressed('w')
                && yChange >= -maxSpeed) {
            yChange -= acceleration;
        } else if (dc.isKeyPressed('s')
                && yChange <= maxSpeed) {
            yChange += acceleration;
        } else {
            if (yChange > 0) {
                yChange -= acceleration * 2;
            } else if (yChange < 0) {
                yChange += acceleration * 2;
            }
            if (Math.abs(yChange) <= 0.15) {
                yChange = 0;
            }
        }

        if (dc.isKeyPressed('d')
                && xChange <= maxSpeed) {
            xChange += acceleration;
        } else if (dc.isKeyPressed('a')
                && xChange >= -maxSpeed) {
            xChange -= acceleration;
        } else {
            if (xChange > 0) {
                xChange -= acceleration * 2;
            } else if (xChange < 0) {
                xChange += acceleration * 2;
            }
            if (Math.abs(xChange) <= 0.15) {
                xChange = 0;
            }
        }
        if (iFrames == 0) { //if you're not in hitstun...
            //max speed diagonally
            if (Math.abs(xChange) > Math.sqrt(maxSpeed)
                    && Math.abs(yChange) > Math.sqrt(maxSpeed)) {
                if (xChange > 0) {
                    xChange = Math.sqrt(maxSpeed);
                } else {
                    xChange = -Math.sqrt(maxSpeed);
                }
                if (yChange > 0) {
                    yChange = Math.sqrt(maxSpeed);
                } else {
                    yChange = -Math.sqrt(maxSpeed);
                }
                //max speed horizontally
            } else if (Math.abs(xChange) > maxSpeed) {
                if (xChange > 0) {
                    xChange = maxSpeed;
                } else {
                    xChange = -maxSpeed;
                }
//                max speed vertically
            } else if (Math.abs(yChange) > maxSpeed) {
                if (yChange > 0) {
                    yChange = maxSpeed;
                } else {
                    yChange = -maxSpeed;
                }
            }
        }
//            stops the player from leaving the screen
        if ((xChange < 0 && x > 2)
                || (xChange > 0 && x < dc.getWidth() - 64)) {
            x += xChange;
        }
        if ((yChange < 0 && y > 2)
                || (yChange > 0 && y < dc.getHeight() - 64)) {
            y += yChange;
        }

        centerX = x + 32;
        centerY = y + 32;

    }

    public void shoot() {

        reloadTime++;//counter for time between shots

        if (dc.isMouseButton(1)
                && reloadTime > shotDelay) {
            reloadTime = 0;
            superShot++;
            b.add(new Bullet(dc, this, 5, this.damage, this.shotSpeed));
        } else if (dc.isMouseButton(3)//big bullet with more than 10 charges (+1 for each normal shot)
                && reloadTime > shotDelay
                && superShot >= 10) {
            reloadTime = 0;
            superShot -= 10;
            b.add(new Bullet(dc, this, 20, this.damage * 2, this.shotSpeed));
        }
    }

    public void createBullet() {//bullet commands
        for (int i = 0; i < b.size(); i++) {
            b.get(i).draw();
            b.get(i).move();
        }
    }

    public void hit(Enemy e) {
        double xDif = e.x - this.centerX;
        double yDif = e.y - this.centerY;

        double distance = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));
        if (distance < this.width / 2 + e.width / 2) {
            iFrames = 50;//delay before you cna be hit again
            health -= e.damage;

            this.angle = Math.atan((yDif) / (xDif));//angle is the opposite of the angle created between the player and the enemy
//            knocks the player away at 15 speed
            if (centerX >= e.x) {
                this.xChange = 15 * Math.cos((this.angle));
                this.yChange = 15 * Math.sin((this.angle));
            } else if (centerX < e.x) {
                this.xChange = -15 * Math.cos((this.angle));
                this.yChange = -15 * Math.sin((this.angle));
            }
        }
    }
}
