package snake;

import DLibX.DConsole;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake {

    public static void main(String[] args) {

        DConsole dc = new DConsole();
        Random rand = new Random();
        dc.setFullscreen(true);

        int speed = 75;

        Square s = new Square(dc, dc.getWidth() / 2 - 3, dc.getHeight() / 2 - 4);
        Grow g = new Grow(dc, (rand.nextInt(31) + 2) * 40, (rand.nextInt(16) + 2) * 40 + 20);
        ArrayList<Follower> f = new ArrayList<>();

        while (true) {
            dc.clear();

            if (dc.isKeyPressed(27)) {
                System.exit(0);
            }

            dc.setBackground(Color.WHITE);

            dc.setOrigin(DConsole.ORIGIN_CENTER);
            dc.setPaint(Color.BLACK);
            dc.fillRect(dc.getWidth() / 2 - 2, dc.getHeight() / 2 - 4, dc.getWidth() - 127, dc.getHeight() - 88);//arena

            s.draw();

            s.prevX = s.x;
            s.prevY = s.y;

            s.move();

            if (f.size() > 0) {

                f.get(0).prevX = s.x;
                f.get(0).prevY = s.y;

                f.get(0).x = s.prevX;
                f.get(0).y = s.prevY;

            }

            for (int i = 1; i < f.size(); i++) {

                if (s.xPos == f.get(i).xPos
                        && s.yPos == f.get(i).yPos) {
                    dc.pause(500);
                    System.exit(0);
                }

                f.get(i).prevX = f.get(i).x;
                f.get(i).prevY = f.get(i).y;

                f.get(i).x = f.get(i - 1).prevX;
                f.get(i).y = f.get(i - 1).prevY;

            }

            for (int i = 0; i < f.size(); i++) {

                f.get(i).draw();

            }

            int x = rand.nextInt(31) + 2;
            int y = rand.nextInt(16) + 2;

            if (g.x == s.x
                    && g.y == s.y) {

                if (f.size() == 0) {
                    for (int i = 0; i < 3; i++) {
                        f.add(new Follower(dc, s.prevX, s.prevY));
                    }
                } else {
                    f.add(new Follower(dc, f.get(f.size() - 1).prevX, f.get(f.size() - 1).prevY));
                }
                g.x = x * 40;
                g.y = y * 40 + 20;
            }

            g.draw();

            s.kill();

            dc.setPaint(Color.BLACK);
            dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
            int length;
            if (f.size() < 3){
                length = 1;
            } else {
                length = f.size() - 1;
            }
            dc.drawString(length, 20, 20);

            dc.pause(speed);
            dc.redraw();
        }
    }

}
