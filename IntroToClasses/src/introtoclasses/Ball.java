package introtoclasses;

import DLibX.DConsole;
import java.awt.Color;
import java.util.Random;

public class Ball {

    private double x;
    private double y;
    private double radius;
    private double angle;
    private double speed;

    private Color color;

    private DConsole cons;

    public Ball(DConsole c) {
        this.cons = c;
        Random r = new Random();
        
        this.radius = r.nextInt(6) + 5;
        
        this.x = r.nextInt(this.cons.getWidth() - 2 * (int)this.radius) + this.radius;
        this.y = r.nextInt(this.cons.getHeight() - 2 * (int)this.radius) + this.radius;

        this.angle = r.nextInt(361);
        this.speed = r.nextInt(10) + 1;

        this.color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        

    }

    public void move() {

        double xChange = speed * Math.cos(Math.toRadians(this.angle));
        double yChange = speed * Math.sin(Math.toRadians(this.angle));

        this.x += xChange;
        this.y += yChange;
        
       if(this.y <= this.radius) {
            this.angle = 180 - (this.angle - 180);
        }
       
        if(this.y >= this.cons.getHeight() - this.radius) {
            this.angle =  -this.angle;
        }
        
        if(this.x <= this.radius) {
            this.angle = 90-(this.angle-90); 
                   
        }
        if(this.x >= this.cons.getWidth() - this.radius) {
            this.angle = 270 - (this.angle - 270);
        }
    }

    public void draw() {
        if (this.x > -this.radius && this.x < this.cons.getWidth() + this.radius
                && this.y > -this.radius && this.y < this.cons.getHeight() + this.radius) {
            this.cons.setOrigin(DConsole.ORIGIN_CENTER);
            this.cons.setPaint(this.color);
            this.cons.fillEllipse(this.x, this.y, this.radius * 2, this.radius * 2);
        }
    }

}
