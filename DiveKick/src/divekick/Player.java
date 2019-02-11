package divekick;

import DLibX.DConsole;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player {

    private DConsole dc;

    private int ground = 450;
    public double x;
    private double xChange = 0;
    public double y;
    private double yChange = 0;
    private double angle = 0;
    public byte direction;
    public short r = 0;
    public short b = 0;
    public int score = 0;
    private int scoreDelay = 0;
    public int startX;
    public int startY;
    public String character;

    //hitbox/hurtbox code
    public double xHit[] = new double[2];
    public double yHit[] = new double[2];
    public double xHurt[] = new double[2];
    public double yHurt[] = new double[2];

    PlayerState state = PlayerState.GROUNDED;//start on the ground

    public Player(DConsole dc) {
        this.dc = dc;
    }

    public void check() {
        
        scoreDelay++;

        if (x <= 5) { //don't go out of bounds
            x = 6;
            xChange = 0;
        }
        if (x >= dc.getWidth() - 45) { //don't go out of bounds
            x = dc.getWidth() - 46;
            xChange = 0;
        }

        x += xChange; //move if you're supposed to
        y -= yChange;

        if (state == PlayerState.GROUNDED) { //if you're on the ground, act like it
            y = ground;
            angle = 0;
            xChange = 0;
        }
        if (state != PlayerState.GROUNDED) { //if you're not on the ground, fall. If you hit the ground, be on the ground
            if (y < ground) {
                yChange -= 0.15;
            } else {
                state = PlayerState.GROUNDED;
            }
        }
        if (state == PlayerState.KICK) {//kick animation and movement

            if (yChange > 0) {
                yChange = 0;
            }
        }
    }

    public void dive() {//jump

        state = PlayerState.DIVE;
        xChange = 0;
        angle = 0;
        yChange = 10;

    }

    public void kick() {//attack

        state = PlayerState.KICK;
        angle = -45;
        xChange = 5 * direction;

    }

    public void draw() {

        dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
        dc.setTransform(AffineTransform.getRotateInstance(Math.toRadians(angle * direction), x + 20, y + 50));//rotate to attack angle if appropriate

        dc.setPaint(new Color(r, 0, b));
        dc.fillRect(x, y, 40, 100);
        dc.setTransform(AffineTransform.getRotateInstance(Math.toRadians(0), x + 20, y + 50));//rotate everything else to 0 degrees
        dc.setOrigin(DConsole.ORIGIN_CENTER);

    }

    public void hit() {
        if (scoreDelay >= 10) {
            score++;
        }
        x = startX;
        y = startY;
        state = PlayerState.GROUNDED;
    }

    public void hurt() {
        x = startX;
        y = startY;
        state = PlayerState.GROUNDED;

    }
}
