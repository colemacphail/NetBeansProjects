package space.simulator;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SpaceSimulator extends JPanel {

    ArrayList<Planet> p = new ArrayList<>();

    public SpaceSimulator() {

        //KEYLISTENER STUFF
        /*
        addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                planet.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                planet.keyPressed(e);
            }
        });
         */
        setFocusable(true);
    }

    private void newPlanet(int x, int y, int w, double a) {
        p.add(new Planet(this, x, y, w, a));
    }

    private void move() {

        System.out.println(p.get(0).getAngle() + ", " + p.get(1).getAngle());
        
        for (int i = 0; i < p.size(); i++) {
            p.get(i).setCenter();
        }

        for (int i = 0; i < p.size(); i++) {
            for (int q = 0; q < p.size(); q++) {
                if (q != i) {
                    p.get(i).setSpeed(p.get(q).getCenterX(), p.get(q).getCenterY());

                }
            }
        }

        for (int i = 0; i < p.size(); i++) {
            p.get(i).move();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < p.size(); i++) {
            p.get(i).paint(g2d);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //JFRAME CREATION
        JFrame frame = new JFrame("Space Simulator");
        SpaceSimulator game = new SpaceSimulator();
        frame.add(game);
        frame.setSize(900, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //

        //SPAWNING ORIGINAL STUFF//
        game.newPlanet(game.getWidth() / 3, game.getHeight() / 2, 20, Math.toRadians(90));
        game.newPlanet(game.getWidth() / 3 * 2, game.getHeight() / 2, 20, Math.toRadians(0));
        //

        //Actual game
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
