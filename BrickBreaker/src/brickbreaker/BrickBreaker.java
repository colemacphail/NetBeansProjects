package brickbreaker;

import DLibX.DConsole;
import java.awt.*;
import java.util.*;

public class BrickBreaker {

    public static void main(String[] args) {
        Random rand = new Random();
        DConsole dc = new DConsole();
        dc.setOrigin(DConsole.ORIGIN_CENTER);

        // ball
        double ballX = 450;
        int ballY = 570;
        double ballXChange = 0;
        int ballYChange = 4;
        double ballXStore = 0;

        // paddle
        double paddleX = 450;
        double paddleSize = 100;

        // bricks
        int[][] brick = new int[10][4];
        int hit = 0;
        int brickNum;

        // extras
        int lives = 3;
        int level = 1;
        int gameMode = 0;
        int menuSelect = 1;
        int pause = -1;
        boolean ballShot = false;
        boolean shot = false;
        double arrowPointX;
        double arrowPointY;

        //powerups
        int powerUp = 1;
        boolean isPowerUp = false;
        int powerUpX = 0;
        double powerUpY = 0;
        boolean bigPaddle = false;
        boolean smallPaddle = false;
        int powerUpWait = 0;
        boolean powerUpActive = false;
        int powerUpDuration = 0;
        int paddleGrowth = 0;

        // give each brick 1 HP
        for (int col = 0; col < brick.length; col++) {
            for (int row = 0; row < brick[0].length; row++) {
                brick[col][row] = 1;
            }
        }

        while (true) {

            dc.clear();

            if (dc.isKeyPressed(32)
                    && gameMode == -1) { // close game
                gameMode = 0;
            }

            if (gameMode == 0) {
                // Drawing menu
                dc.setPaint(Color.BLACK);
                dc.setFont(new Font("Cooper Black", Font.BOLD, 60));
                dc.drawString("PLAY", 450, 200);
                dc.drawString("INSTRUCTIONS", 450, 400);
                dc.setFont(new Font("Cooper Black", Font.BOLD, 40));
                dc.drawString("Q TO SELECT", 450, 50);
                // selecting menu
                if (menuSelect == 1
                        && dc.isKeyPressed(40)) {
                    menuSelect = 2;
                    dc.pause(100);
                } else if (menuSelect == 2
                        && dc.isKeyPressed(38)) {
                    menuSelect = 1;
                    dc.pause(100);
                }
                // arrow
                dc.fillPolygon(new int[]{125, 50, 50}, new int[]{menuSelect * 200 + 75, menuSelect * 200 + 25, menuSelect * 200 + 125});

                if (dc.isKeyPressed('q')
                        && menuSelect == 1) {//if on play
                    gameMode = 1;//start game
                }
                if (dc.isKeyPressed('q')
                        && menuSelect == 2) {//if on instructions
                    gameMode = -1;//go to instructions
                }
            }

            if (gameMode == -1) {
                dc.setPaint(Color.BLACK);
                dc.setFont(new Font("Cooper Black", Font.BOLD, 40));
                dc.drawString("LEFT / RIGHT ARROWS TO MOVE", 450, 50);
                dc.drawString("P TO PAUSE", 450, 200);
                dc.drawString("ESC TO CLOSE", 450, 350);
                dc.drawString("SPACE TO GO BACK", 450, 500);

            }

            if (gameMode == 1) {
                // ---- MOVEMENT -------------------------
                // ball movement
                if (lives > 0) {

                    if (dc.isKeyPressed('p')) { // p to pause
                        pause = -pause;
                        dc.pause(300);
                    }
                    if (pause == -1) {// if not paused
                        if (ballShot) { // if ball has been shot
                            if (!shot) { // on first time shooting
                                if (ballXStore > 5) {
                                    ballXStore = 5;
                                }
                                if (ballXStore < -5) {
                                    ballXStore = -5;
                                }
                                ballXChange = ballXStore; // shot angle dependent on stored X value
                                ballXStore = 0;
                                shot = true; // only run once

                            }
                            ballX = ballX + ballXChange;
                            ballY = ballY + ballYChange;
                        }

                        if (!ballShot) { //if still holding the ball
                            ballY = 570;
                            ballX = paddleX;

                            arrowPointX = ballX + ballXStore * 52;
                            arrowPointY = 0.0008 * (Math.pow(arrowPointX - ballX, 2)) + 300;

                            if (arrowPointX < ballX - 250) {
                                arrowPointX = ballX - 250;
                                ballXStore = -5;
                            }
                            if (arrowPointX > ballX + 250) {
                                arrowPointX = ballX + 250;
                                ballXStore = 5;
                            }

                            dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);// drawing the angle arrow
                            dc.fillPolygon(new double[]{arrowPointX, ballX + 3, ballX - 3}, new double[]{arrowPointY, ballY, ballY});
                            dc.setOrigin(DConsole.ORIGIN_CENTER);

                            if (dc.isKeyPressed('a')) { // angle left
                                ballXStore = ballXStore - 0.2;
                            }
                            if (dc.isKeyPressed('d')) { // angle right
                                ballXStore = ballXStore + 0.2;
                            }

                            dc.drawString("SPACE TO SHOOT", 450, 400);
                            dc.drawString("A AND D TO CHANGE ANGLE", 450, 300);
                            if (dc.isKeyPressed(32)) {
                                ballShot = true;
                            }
                        }
                        // paddle
                        if (dc.isKeyPressed(37)) {
                            paddleX = paddleX - 7;
                        }
                        if (dc.isKeyPressed(39)) {
                            paddleX = paddleX + 7;
                        }
                        if (paddleX > 900 - ((paddleSize + paddleGrowth) / 2)) {//stop at right
                            paddleX = 900 - ((paddleSize + paddleGrowth) / 2);
                        }
                        if (paddleX < ((paddleSize + paddleGrowth) / 2)) {//stop at left
                            paddleX = ((paddleSize + paddleGrowth) / 2);
                        }
                    }
                    // ---- COLLISION CHECKS -----------------
                    // --- Ball hitting the walls --
                    // top
                    if (ballY <= 10) {
                        ballYChange = -ballYChange;
                    }

                    // left
                    if (ballX <= 10) {
                        ballXChange = -ballXChange;
                    }
                    if (ballX < 5) {
                        ballX = 15;
                    }

                    // right
                    if (ballX >= 890) {
                        ballXChange = -ballXChange;
                    }
                    if (ballX > 895) {
                        ballX = 885;
                    }

                    // went through the bottom
                    if (ballY >= 610) {
                        ballX = 450; // teleport back to the middle
                        ballY = 300;
                        lives--;
                        paddleX = 450;
                        ballXChange = 0;
                        ballShot = false;
                        shot = false;

                    }

                    // hit the paddle
                    if (ballX >= paddleX - ((paddleSize + paddleGrowth) / 2 + 10)
                            && ballX <= paddleX + ((paddleSize + paddleGrowth) / 2 + 10)
                            && ballY >= 570
                            && ballY <= 590) {
                        ballYChange = -4; // go up
                        ballXChange = (ballX - paddleX) / ((paddleSize + paddleGrowth) / 8);
                    }

                    brickNum = 0; // reset to 0 before adding to the number of bricks

                    // hit brick
                    for (int col = 0; col < brick.length; col++) { // bricks across
                        for (int row = 0; row < brick[0].length; row++) { // bricks down

                            int brickX = col * 90 + 40;
                            int brickY = row * 30 + 100;

                            if (brick[col][row] > 0) { //if the brick exists

                                brickNum++; // add 1 to number of bricks for each that spawns

                                dc.setPaint(Color.WHITE);
                                dc.setFont(new Font("Cooper Black", Font.BOLD, 20));
                                dc.drawString(brick[col][row], brickX, brickY);

                                if (ballX >= brickX - 50 //top
                                        && ballX <= brickX + 50
                                        && ballY >= brickY - 25
                                        && ballY <= brickY - 20) {
                                    ballYChange = -4; //bounce
                                    brick[col][row]--;
                                    hit++;// reduce brick health by 1

                                }

                                if (ballX >= brickX - 50 // bottom
                                        && ballX <= brickX + 50
                                        && ballY >= brickY + 20
                                        && ballY <= brickY + 25) {
                                    ballYChange = 4; //bounce
                                    brick[col][row]--;
                                    hit++;// reduce brick health by 1

                                }
                                if (ballX <= brickX - 45 //left
                                        && ballX >= brickX - 55
                                        && ballY >= brickY - 25
                                        && ballY <= brickY + 25
                                        && ballXChange > 0) {
                                    ballXChange = -Math.abs(ballXChange); //bounce
                                    brick[col][row]--;
                                    hit++; // reduce brick health by 1

                                }

                                if (ballX >= brickX + 45 // right
                                        && ballX <= brickX + 55
                                        && ballY >= brickY - 25
                                        && ballY <= brickY + 25
                                        && ballXChange <= 0) {
                                    ballXChange = Math.abs(ballXChange); //bounce
                                    brick[col][row]--;
                                    hit++; // reduce brick health by 1

                                }

                                if (brick[col][row] == 0
                                        && isPowerUp == false
                                        && powerUpWait >= 1000) {
                                    powerUp = rand.nextInt(3) + 1;
                                    isPowerUp = true;
                                    powerUpX = brickX;
                                    powerUpY = brickY;

                                }

                            }
                        }
                    }

                    // ---- DRAWING --------------------------
                    // ball
                    dc.setPaint(Color.BLACK);
                    dc.fillEllipse(ballX, ballY, 20, 20);

                    // paddle
                    if (bigPaddle) {
                        if (powerUpDuration <= 100) {
                            paddleGrowth++;
                        }

                        if (powerUpDuration >= 400) {
                            paddleGrowth--;
                        }

                    } else if (smallPaddle) {

                        if (powerUpDuration <= 50) {
                            paddleGrowth--;
                        }

                        if (powerUpDuration >= 450) {
                            paddleGrowth++;
                        }

                    } else {
                        paddleSize = 100;
                        paddleGrowth = 0;
                    }

                    dc.fillRect(paddleX, 590, paddleSize + paddleGrowth, 20);

                    // draw bricks
                    for (int col = 0; col < brick.length; col++) { // draw bricks across
                        for (int row = 0; row < brick[0].length; row++) { // draw bricks down

                            if (brick[col][row] > 0) { // if the brick exists

                                int brickX = col * 90 + 40;
                                int brickY = row * 30 + 100;

                                dc.setPaint(Color.BLACK);
                                dc.fillRect(brickX, brickY, 90, 30);

                                dc.setPaint(Color.WHITE);
                                dc.drawRect(brickX, brickY, 90, 30);

                                dc.drawString(brick[col][row], brickX, brickY - 5);
                            }
                            if (brickNum < 1) {
                                level++; // next level
                                lives++; // extra life
                                brickNum = brick.length * brick[0].length; // Reset nuber of bricks
                                ballX = 450;
                                ballY = 300;
                                paddleX = 450;
                                ballXChange = 0;
                                ballYChange = 4;
                                ballShot = false;
                                arrowPointX = ballX + ballXStore * 52;

                                for (col = 0; col < brick.length; col++) {
                                    for (row = 0; row < brick[0].length; row++) {
                                        brick[col][3] = level; //lowest row health = level
                                        brick[col][2] = level - 1; // the higher the row the lower the health
                                        brick[col][1] = level - 2;
                                        brick[col][0] = level - 3;
                                        if (brick[col][3] < 1) {
                                            brick[col][3] = 1;
                                        }
                                        if (brick[col][2] < 1) {
                                            brick[col][2] = 1;
                                        }
                                        if (brick[col][1] < 1) {
                                            brick[col][1] = 1;
                                        }
                                        if (brick[col][0] < 1) {
                                            brick[col][0] = 1;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // ---- FEATURES -------------------------
                    //lives
                    dc.setPaint(Color.BLACK);
                    dc.setFont(new Font("Cooper Black", Font.BOLD, 40));
                    if (pause == -1) {
                        if (lives > 1) {
                            dc.drawString(lives + " lives", 150, 50);
                        }
                        if (lives == 1) {
                            dc.drawString(lives + " life", 150, 50);
                        }
                        dc.drawString(brickNum + " bricks", 450, 50);
                        dc.drawString("level " + level, 750, 50);
                    } else {
                        dc.drawString("P TO UNPAUSE", 450, 50);
                    }

                    if (dc.isKeyPressed(27)) {
                        System.exit(0);
                    }

                    // ---- POWERUPS -------------------------
                    powerUpWait++;

                    // powerup existing
                    if (isPowerUp) {

                        powerUpWait = 0;
                        powerUpY = powerUpY + 3;

                        if (powerUp == 1) {
                            dc.setPaint(new Color(0, 255, 0));//draw big paddle powerup
                            dc.fillRect(powerUpX, powerUpY, 40, 40);
                            dc.setPaint(new Color(255, 255, 255));
                            dc.fillRect(powerUpX, powerUpY, 30, 10);
                        }

                        if (powerUp == 2) {
                            dc.setPaint(new Color(255, 0, 0));//draw small paddle powerup
                            dc.fillRect(powerUpX, powerUpY, 40, 40);
                            dc.setPaint(new Color(255, 255, 255));
                            dc.fillRect(powerUpX, powerUpY, 30, 10);
                        }

                        if (powerUp == 3) {
                            dc.setPaint(new Color(0, 255, 0));//draw add life powerup
                            dc.fillRect(powerUpX, powerUpY, 40, 40);
                            dc.setPaint(new Color(255, 255, 255));
                            dc.fillRect(powerUpX, powerUpY, 30, 10);
                            dc.fillRect(powerUpX, powerUpY, 10, 30);
                        }

                        if (powerUpY > 640) {
                            isPowerUp = false;
                        }
                        if (powerUpX <= paddleX + (paddleSize / 2) + 20
                                && powerUpX >= paddleX - (paddleSize / 2) - 20
                                && powerUpY >= 560
                                && powerUpY <= 620) {
                            powerUpActive = true; // the power up has touched the paddle
                            isPowerUp = false;
                            if (powerUp == 1) {
                                bigPaddle = true;//enable big paddle powerup
                            }
                            if (powerUp == 2) {
                                smallPaddle = true;
                            }
                            if (powerUp == 3) {
                                lives++;
                            }
                        }
                    }

                    //after hitting powerup
                    if (powerUpActive == true) {
                        powerUpDuration++;
                        if (powerUpDuration >= 500) { // 5ish seconds
                            bigPaddle = false;
                            smallPaddle = false;
                            powerUpActive = false; // deactivate powerup
                            powerUpDuration = 0; //reset timer
                        }
                    }

                }
                // end game
                if (lives == 0) {
                    //font
                    if (dc.isKeyPressed(27)) {
                        System.exit(0);
                    }
                    dc.setPaint(Color.BLACK);
                    dc.drawString("YOU LOSE", 450, 200);
                    dc.drawString("ESC TO CLOSE", 450, 400);
                }
            }
            dc.redraw();
            dc.pause(10);
        }
    }
}