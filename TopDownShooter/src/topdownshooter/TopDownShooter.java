package topdownshooter;

import DLibX.DConsole;
import java.awt.Font;
import java.awt.Color;
import java.io.*;
import java.util.*;

enum GameState {
    GAME, LOSE, MENU, WAVE_BREAK
}

public class TopDownShooter {

    public static void main(String[] args) {

        DConsole dc = new DConsole();

        Player p = new Player(dc, dc.getWidth() / 2, dc.getHeight() / 2);
        ArrayList<Enemy> e = new ArrayList<>();
        Button[] b = new Button[2]; // 2 menu buttons
        GameState state;

        int wave = 0;
        int numOfEnemies = 0;
        boolean waveBreakFlag = false;
        int waveBreakTime = 0;

        state = GameState.MENU;//start at the menu
        dc.setFullscreen(true);

        while (true) {

            dc.clear();

            if (dc.isKeyPressed(27)) {//esc
                if (state != GameState.MENU) {//save
                    try (PrintStream save = new PrintStream(new File("C:\\Users\\Cole\\Documents\\NetBeansProjects\\TopDownShooter\\save.txt"))) {

                        //first line is health, second is the wave, every line after that is items
                        save.println(p.health);
                        save.println(wave);
                        save.println(p.superShot);
                        for (int i = 0; i < p.items.size(); i++) {
                            save.println(p.items.get(i));
                        }
                        save.close();
                    } catch (FileNotFoundException f) {
                        System.out.println("Oops I can't find that. Check permissions or path or something.");
                    }
                }

                System.exit(0);

            }

            if (state == GameState.MENU) { //TODO: Improve the menu -- mostly making it look nicer

                dc.setFont(new Font("Roboto", Font.BOLD, 60));

                //create the buttons
                b[0] = new Button(dc, dc.getWidth() / 2, dc.getHeight() / 3, 600, 100, "NEW GAME");
                b[1] = new Button(dc, dc.getWidth() / 2, dc.getHeight() * 2 / 3, 600, 100, "CONTINUE");

                for (int i = 0; i < b.length; i++) {
                    b[i].draw();
                }

                if (b[0].clicked()) {//new game
                    wave = 0;
                    p.items.clear();
                    state = GameState.GAME;
                }

                if (b[1].clicked()) {//continue
                    Scanner sc = null;

                    try {
                        sc = new Scanner(new File("C:\\Users\\Cole\\Documents\\NetBeansProjects\\TopDownShooter\\save.txt"));//directory for save file
                    } catch (FileNotFoundException ex) {
                        System.out.println("Oops I can't find that. Check permissions or pathing or something.");
                    }

                    p.health = sc.nextInt();//load health
                    if (p.health > 0) {
                        wave = sc.nextInt() - 1;//load wave
                        waveBreakFlag = true;
                        state = GameState.WAVE_BREAK;
                        p.superShot = sc.nextInt();

                        while (sc.hasNextLine()) {//go until the end of the save file
                            p.items.add(sc.nextLine());//load all items
                        }
                        p.loadStats();
                    } else {
                        p.health = 3;
                        wave = 0;
                        p.items.clear();
                    }

                    state = GameState.GAME;
                }

            }

            if (state == GameState.GAME
                    || state == GameState.WAVE_BREAK) {//if in a playable state

                if (numOfEnemies <= 0
                        && !waveBreakFlag
                        && wave > 0) {
                    state = GameState.WAVE_BREAK;
                    waveBreakTime = 0;
                    waveBreakFlag = true;//only start the break between waves once
                    int i = 0;
                    do {
                        addPowerUp(p);
                        i++;
                    } while (i < 3 && wave % 5 == 0);
                }

                if (state != GameState.WAVE_BREAK) {

                    if (numOfEnemies <= 0) { // when the last enemy dies...

                        wave++;

                        if (wave % 5 != 0) {

                            /*
                            add wave * 2 + 1 enemies 
                            3 on wave 1, then +2 per wave
                            number of enemies resets every 5 waves, but keeps their health / speed growth steady
                            adds 1 to min / max enemies every 5 waves
                             */
                            for (int i = 0; i < ((wave % 5) * 2) + 1 + wave / 5; i++) {
                                spawnEnemy(dc, e, wave);
                                numOfEnemies++;
                            }
                        } else { //boss round
                            spawnBoss(dc, e, wave);
                            numOfEnemies++;
                        }
                    }

                    waveBreakFlag = false;
                }

                if (p.iFrames > 0) {//if you were just hit start timer until you can be hit again
                    p.iFrames--;
                }
                p.move();
                if (p.iFrames == 0) {//if you haven't just been hit
                    for (int i = 0; i < e.size(); i++) {
                        p.hit(e.get(i));
                    }
                }

                p.createBullet();//bullet commands
                p.shoot();
                p.draw();
                if (p.health <= 0) {//kill player
                    state = GameState.LOSE;
                }

                for (int i = 0; i < e.size(); i++) {//enemy functions
                    e.get(i).draw();
                    e.get(i).hit(p);
                    e.get(i).move(p);

                    if (e.get(i).health <= 0) {//kill
                        e.remove(i);
                        numOfEnemies--;
                    }
                }
                for (int i = p.b.size() - 1; i > 0; i--) {//bullet out of bounds --> remove it
                    if (p.b.get(i).x < -p.b.get(i).width
                            || p.b.get(i).x > dc.getWidth()
                            || p.b.get(i).y < -p.b.get(i).width
                            || p.b.get(i).y > dc.getHeight()) {
                        p.b.remove(i);
                    }
                }

                drawMap(dc);
                dc.setFont(new Font("Roboto", Font.BOLD, 20));
                dc.setPaint(Color.BLACK);
                dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
                dc.drawString("WAVE " + wave, 10, 10); // tell the player what wave they're on
                drawStats(dc, p);//give the player their stats
            }

            if (state == GameState.WAVE_BREAK) {
                waveBreakTime++;
                if (waveBreakTime > 150) { // 1.5 secondbreak between waves
                    state = GameState.GAME;
                }
            }

            if (state == GameState.LOSE) {
                dc.setOrigin(DConsole.ORIGIN_CENTER);
                wave = 0;
                dc.drawString("GAME OVER!", dc.getWidth() / 2, dc.getHeight() / 2);
            }

            //TODO: Make a "WIN" state
            dc.pause(10);
            dc.redraw();
        }
    }
    //* FUNCTIONS *//

    static void drawMap(DConsole dc) {
        dc.setOrigin(DConsole.ORIGIN_CENTER);
        dc.setBackground(new Color(102, 51, 0));
        dc.setPaint(new Color(17, 138, 43));

        int radius = 40;
        int diameter = radius * 2;

        for (int i = 0; i < 20; i++) {// draw top/bottom trees
            double x = (radius / 2) + radius * i * 2;
            dc.fillEllipse(x, 0, diameter, diameter);
            dc.fillEllipse(x, dc.getHeight(), diameter, diameter);
        }

        for (int i = 0; i < 12; i++) {// draw side trees
            double y = (radius / 2) + radius * i * 2;
            dc.fillEllipse(0, y, diameter, diameter);
            dc.fillEllipse(dc.getWidth(), y, diameter, diameter);
        }

    }

    static void drawStats(DConsole dc, Player p) {//show they player their stats
        dc.setOrigin(DConsole.ORIGIN_TOP_RIGHT);
        dc.drawString("DAMAGE: " + p.damage, dc.getWidth() - 50, 40);
        dc.drawString("RELOAD TIME: " + p.shotDelay, dc.getWidth() - 50, 80);
        dc.drawString("SPEED: " + p.maxSpeed, dc.getWidth() - 50, 120);
        dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
    }

    static void addPowerUp(Player p) {
        Random rand = new Random();
        List<String> powerUp = Arrays.asList("damage", "bullets", "speed", "health");

        String newPowerUp = powerUp.get(rand.nextInt(powerUp.size()));
        p.items.add(newPowerUp);//add a random item to the arraylist of items
        p.addPowerUp(newPowerUp);
        System.out.println(newPowerUp + " added!");
    }

    static void spawnEnemy(DConsole dc, ArrayList<Enemy> e, int wave) {

        Random rand = new Random();
        List<String> side = Arrays.asList("top", "bottom", "left", "right");

        String randomSide = side.get(rand.nextInt(side.size()));
        switch (randomSide) {//choose which side the enemy randomly spawns on
            // when creating an enemy: DConsole, x, y, starting health
            case "top":
                e.add(new Enemy(dc, rand.nextInt(dc.getWidth()), -64, (rand.nextInt((wave - 1) * 3 + 1) + 10)));
                break;
            case "bottom":
                e.add(new Enemy(dc, rand.nextInt(dc.getWidth()), dc.getHeight() + 64, (rand.nextInt((wave - 1) * 3 + 1) + 10)));
                break;
            case "left":
                e.add(new Enemy(dc, -64, rand.nextInt(dc.getHeight()), (rand.nextInt((wave - 1) * 3 + 1) + 10)));
                break;
            default: // right
                e.add(new Enemy(dc, dc.getWidth() + 64, rand.nextInt(dc.getHeight()), (rand.nextInt((wave - 1) * 3 + 1) + 10)));
                break;
        }

    }

    static void spawnBoss(DConsole dc, ArrayList<Enemy> e, int w) {

        int wave = w / 5; // which boss it is (first, second, etc)

        // when creating a boss: DConsole, x, y, width, height, speed, base health, damage, colour
        if (wave == 1) {
            // spawn the first boss
            e.add(new Enemy(dc, dc.getWidth() / 2, 0, 512, 512, 1, 100, 2, Color.BLACK));
        } else if (wave == 2) {
            // spawn the second boss
            e.add(new Enemy(dc, dc.getWidth() / 2, 0, 16, 16, 3, 50, 2, Color.WHITE));

        }
        //TODO: Make more bosses
    }

}
