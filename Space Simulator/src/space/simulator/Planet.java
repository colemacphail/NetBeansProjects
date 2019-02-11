package space.simulator;

import java.awt.Graphics2D;

public class Planet {

    private double x;
    private double y;
    private double width;
    private double centerX;
    private double centerY;
    private double xa;
    private double ya;
    private double angle;
    private double speed = 0;

    private SpaceSimulator s;

    public Planet(SpaceSimulator s, int x, int y, int w, double a) {
        this.s = s;
        this.x = x;
        this.y = y;
        this.width = w;
        this.angle = a;
    }

    public double getCenterX() {
        return this.centerX;
    }

    public double getCenterY() {
        return this.centerY;
    }

    public double getAngle() {
        return Math.toDegrees(angle);
    }

    public void setCenter() {

        this.centerX = this.x + this.width / 2;
        this.centerY = this.y + this.width / 2;

    }

    public void setSpeed(double x, double y) {

        double xDif = this.centerX - x;
        double yDif = this.centerY - y;

        double distance = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));

        this.speed = 1000 / distance;

        if (x >= centerX) {
            this.xa = (((this.speed) * Math.cos((this.angle))));
            this.ya = (((this.speed) * Math.sin((this.angle))));
        } else if (x < centerX) {
            this.xa = (((-this.speed) * Math.cos((this.angle))));
            this.ya = (((-this.speed) * Math.sin((this.angle))));
        }
    }

    public void move() {

        this.x += this.xa;
        this.y += this.ya;

    }

    public void paint(Graphics2D g) {

        g.fillOval((int) this.x, (int) this.y, (int) this.width, (int) width);

    }
}
