package mario.party;

import DLibX.DConsole;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Cole
 */
public class Game {

    int cameraOffsetX;
    int cameraOffsetY;
    int centerScreenX;
    int centerScreenY;
    int numOfPlayers;
    Map map = Map.BASIC;

    DConsole dc = new DConsole();
    Player[] p;
    Random rand = new Random();
    ArrayList<Position> pos = new ArrayList<>();
    MapGenerator m;

    public Game(int n, Map m) {
        this.map = m;
        this.numOfPlayers = n;
        this.m = new MapGenerator(this.dc, this.map, this.pos);
    }
    
    public void run(){
        this.init();
        
        while(true){
            this.periodic();
        }
    }

    private void init() {
        
        this.p = new Player[numOfPlayers];
        this.m.mapConfig();

        for (int i = 0; i < p.length; i++) {
            Color c = null;
            switch (i) {
                case 0:
                    c = Color.RED;
                    break;
                case 1:
                    c = Color.BLUE;
                    break;
                case 2:
                    c = Color.GREEN;
                    break;
                case 3:
                    c = Color.YELLOW;
                    break;
                default:
                    c = Color.BLACK;
                    break;
            }
            this.p[i] = new Player(dc, this, this.pos, c, i);
        }
    }

    private void periodic() {
        this.dc.clear();

        this.centerScreenX = this.dc.getWidth() / 2;
        this.centerScreenY = this.dc.getHeight() / 2;

        for (int i = 0; i < this.numOfPlayers; i++) {
            
            this.p[i].setSpacesToMove(this.roll());
            
            this.dc.clear();
            this.dc.redraw();
            
            this.p[i].run();
        }

        this.dc.redraw();
        this.dc.pause(20);
    }

    private int roll() {
        int r = 0;
        while (!dc.isMouseButton(1)) {

            this.dc.clear();

            r = rand.nextInt(10) + 1;
            
            this.dc.setPaint(Color.BLACK);
            this.dc.setOrigin(DConsole.ORIGIN_CENTER);
            this.dc.drawRect(dc.getWidth() / 2, dc.getHeight() / 2, 400, 400);
            this.dc.setFont(new Font("Cooper Black", Font.BOLD, 72));
            this.dc.drawString(r, this.centerScreenX, this.centerScreenY);

            this.dc.redraw();
            this.dc.pause(20);
        }
        dc.pause(1000);
        return r;
    }

    public int getXOffset() {
        return -this.cameraOffsetX;
    }

    public int getYOffset() {
        return -this.cameraOffsetY;
    }
}
