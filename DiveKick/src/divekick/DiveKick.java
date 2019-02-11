package divekick;

import DLibX.DConsole;
import java.awt.*;

enum PlayerState {
    DIVE, KICK, GROUNDED
}

enum GameState {
    GAME, PAUSE, WIN, MENU, SETTINGS, CHAR_SELECT
}

enum Button {
    MULTIPLAYER, SETTINGS,
    CHAR_DIVE, CHAR_KICK
}

public class DiveKick {

    public static void main(String[] args) {

        DConsole dc = new DConsole();

        Player p[] = new Player[2];
        Buttons b[] = new Buttons[2];

        boolean pause = false;
        double distance = 0;
        String character[] = new String[2];
        GameState gameState = GameState.GAME;

        dc.setFullscreen(true);

        for (int i = 0; i < p.length; i++) {
            p[i] = new Player(dc);
            p[i].startX = i * 1200 + 50;
            p[i].startY = 450;
            p[i].x = p[i].startX;
            p[i].y = p[i].startY;
        }

        for (int i = 0; i < b.length; i++) {
            b[i] = new Buttons(dc);
            b[i].x = dc.getWidth() / 2;
            b[i].y = dc.getHeight() / 3 * (i + 1);
        }

        //define possible characters
        character[0] = "DIVE";
        character[1] = "KICK";

        //starting directions and colours
        p[0].direction = 1;
        p[0].r = 255;

        p[1].direction = -1;
        p[1].b = 255;

        while (true) {

            for (int i = 0; i < b.length; i++) {
                if (b[i].active) {//button functions
                    if (b[i].button == Button.MULTIPLAYER) {
                        gameState = GameState.CHAR_SELECT;
                    }
                    if (b[i].button == Button.CHAR_DIVE) {

                    }
                }
            }

            if (dc.isKeyPressed(27)) {//esc
                System.exit(0);
            }

            if (gameState == GameState.MENU) {

                b[0].button = Button.MULTIPLAYER;
                b[1].button = Button.SETTINGS;

                dc.clear();

                for (int i = 0; i < b.length; i++) {
                    b[i].draw(p);
                }

                dc.redraw();
            }
            if (gameState == GameState.CHAR_SELECT) {

                dc.redraw();
                b[0].button = Button.CHAR_DIVE;
                b[1].button = Button.CHAR_KICK;
                
                for (int i = 0; i < b.length; i++) {
                    b[i].draw(p);
                }

                if (dc.isKeyPressed(13)) {//enter
                    gameState = GameState.GAME;
                }
            }
            if (gameState == GameState.GAME
                    || gameState == GameState.PAUSE) {
                //define where the hit/hurt boxes are
                for (int i = 0; i < p[0].xHit.length; i++) {
                    for (int q = 0; q < p.length; q++) {

                        p[q].xHit[i] = p[q].x + 20 + (20 + 30 * i) * p[q].direction;
                        p[q].yHit[i] = p[q].y + 100 - 30 * i;
                        p[q].xHurt[i] = p[q].x + 20 + (20 - 30 * i) * p[q].direction;
                        p[q].yHurt[i] = p[q].y + 70 - 30 * i;

                    }
                }

                if (dc.isKeyPressed(' ')) {
                    pause = !pause;
                    dc.pause(500);
                }
                if (pause) {
                    gameState = GameState.PAUSE;
                } else {
                    gameState = GameState.GAME;
                }

                if (gameState != GameState.PAUSE) {
                    dc.clear();

                    //p1 controls
                    if (dc.isKeyPressed('w')
                            && p[0].state == PlayerState.GROUNDED) {
                        p[0].dive();

                    }

                    if (dc.isKeyPressed('d')
                            && !dc.isKeyPressed('w')
                            && p[0].state == PlayerState.DIVE) {

                        p[0].kick();
                    }
                    //p2 controls
                    if (dc.isKeyPressed('i')
                            && p[1].state == PlayerState.GROUNDED) {
                        p[1].dive();

                    }
                    if (dc.isKeyPressed('j')
                            && !dc.isKeyPressed('i')
                            && p[1].state == PlayerState.DIVE) {

                        p[1].kick();
                    }
                    //change directions of the players based on relative positions
                    if (p[0].x <= p[1].x) {
                        if (p[0].state != PlayerState.KICK) {
                            p[0].direction = 1;
                        }
                        if (p[1].state != PlayerState.KICK) {
                            p[1].direction = -1;
                        }
                    } else if (p[0].x > p[1].x) {
                        if (p[0].state != PlayerState.KICK) {
                            p[0].direction = -1;
                        }
                        if (p[1].state != PlayerState.KICK) {
                            p[1].direction = 1;
                        }
                    }

                    for (int i = 0; i < p.length; i++) {//collision detection (with walls) and drawing

                        p[i].check();
                        p[i].draw();

                    }

                    //hitboxes
                    for (int q = 0; q < p.length; q++) {
                        for (int r = p.length - 1; r > -1; r--) {
                            for (int i = 0; i < p[0].xHit.length; i++) {

                                //find how far away the hitboxes are
                                distance = Math.sqrt(Math.abs(p[q].xHit[i] - p[r].xHurt[i]) * 2 + Math.abs(p[q].yHit[i] - p[r].yHurt[i]) * 2);

//                            dc.setPaint(Color.BLACK);
//                            dc.fillEllipse(p[q].xHit[i], p[q].yHit[i], 10, 10);//draw where the hitboxes are -- remove later
//                            dc.fillEllipse(p[q].xHurt[i], p[q].yHurt[i], 40, 40);
                                if (p[q].state == PlayerState.KICK) {
                                    if (p[r].state != PlayerState.KICK) {
                                        if (p[q].xHit[i] > p[r].x
                                                && p[q].xHit[i] < p[r].x + 40
                                                && p[q].yHit[i] > p[r].y
                                                && p[q].yHit[i] < p[r].y + 80) {

                                            p[q].hit();
                                            p[r].hurt();
                                            break;

                                        }
                                    } else if (p[r].state == PlayerState.KICK) {
                                        if (distance <= 7.7) {

                                            p[q].hit();
                                            p[r].hurt();
                                            break;

                                        }
                                    }
                                }
                                //print scores
                                dc.setFont(new Font("Cooper Black", Font.BOLD, 60));
                                dc.setPaint(Color.BLACK);
                                dc.drawString(p[q].score, 200 + 1000 * q, 100);
                            }
                        }
                    }
                }

                //ground
                dc.setOrigin(DConsole.ORIGIN_CENTER);
                dc.setPaint(Color.BLACK);
                dc.fillRect(675, 675, 1400, 250);

                dc.pause(0);
                dc.redraw();
            }
        }
    }
}
