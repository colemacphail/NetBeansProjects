package introtoclasses;

import DLibX.DConsole;

public class IntroToClasses {

    public static void main(String[] args) {

        DConsole d = new DConsole();

        /*Ball b = new Ball(d);
         Ball b2 = new Ball(d);
         */
        Ball[] balls = new Ball[100];

        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(d);
        }

        while (true) {
            d.clear();

            for (int i = 0; i < balls.length; i++) {
                balls[i].move();
                 balls[i].draw();
            }

            
            d.redraw();
            d.pause(20);
        }
    }

}
