package cellular.automata;

import DLibX.DConsole;
import java.awt.Color;
import java.util.Random;

public class Cell {

    public enum Team {
        RED, BLUE, CYAN, PINK, YELLOW, BLACK, WHITE, MAGENTA
    }

    private DConsole dc;

    public Team team;
    public Color color;
    Color land = new Color(17, 138, 43);
    Color water = new Color(36, 131, 248);
    public short x;
    public short y;
    public float fitness;
    private float age;
    public short baseStrength;
    public short damageTaken = 0;
    Random rand = new Random();
    
    String chosenDirection;

    public Cell(DConsole dc, Team team, int baseStrength, short x, short y){
        this.dc = dc;
        this.team = team;
        this.baseStrength = (short) (baseStrength + (rand.nextInt(7) - 3));
        this.x = x;
        this.y = y;
    }

    public void chooseDirection() {
        
        int direction = rand.nextInt(2);
        int distance = rand.nextInt(3) - 1;
        if (direction == 0
                && checkColor(this.x + distance, this.y)){
            this.x += distance;
        } else if (direction == 1
                && checkColor(this.x, this.y + distance)){
            this.y += distance;
        }
    }

    public boolean checkColor(int x, int y) {
        Color color;
        color = (dc.getPixelColor(x, y));
        return !color.equals(water);
                
    }

    public boolean canReproduce() {

        int chance = rand.nextInt(8);

        return this.age > 15
                && chance < 4;
    }

    public boolean canDie() {

        int chance = rand.nextInt(6);

        return (fitness < 5
                && chance < 3)
                || fitness < 0;
    }

    public void draw() {
        if (this.team == Team.RED) {
            this.color = Color.RED;
        } else if (this.team == Team.BLUE) {
            this.color = Color.BLUE;
        } else if (this.team == Team.CYAN) {
            this.color = Color.CYAN;
        } else if (this.team == Team.PINK) {
            this.color = Color.PINK;
        } else if (this.team == Team.YELLOW) {
            this.color = Color.YELLOW;
        } else if (this.team == Team.BLACK) {
            this.color = Color.BLACK;
        } else if (this.team == Team.WHITE) {
            this.color = Color.WHITE;
        } else if (this.team == Team.MAGENTA) {
            this.color = Color.MAGENTA;
        }
        dc.setOrigin(DConsole.ORIGIN_CENTER);
        dc.setPaint(this.color);
        dc.fillRect(this.x, this.y, 1, 1);
    }

    public void move() {

        chooseDirection();

        //wrap around sides/top
        if (this.x > dc.getWidth() - 1) {
            this.x = 1;
        }
        if (this.x < 1) {
            this.x = (short) (dc.getWidth() - 1);
        }
        if (this.y > dc.getHeight() - 1) {
            this.y = 1;
        }
        if (this.y < 1) {
            this.y = (short) (dc.getHeight() - 1);
        }
    }

    public void grow() {
        this.fitness = (float) (-0.0075 * (Math.pow((this.age - 25), 2)) + baseStrength - damageTaken);
        this.age++;
    }

    public boolean drowning() {
        return !checkColor(this.x + 1, this.y)
                && !checkColor(this.x - 1, this.y)
                && !checkColor(this.x, this.y + 1)
                && !checkColor(this.x, this.y - 1);
    }
}
