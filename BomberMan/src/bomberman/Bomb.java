package bomberman;

import DLibX.DConsole;

public class Bomb {

    private DConsole dc;

    public boolean isPlaced;
    public int placedBy = 0;
    public int x;
    public int y;
    public int xPos;
    public int yPos;
    private int delay = 0;
    public int size;
    private boolean tStop = false;
    private boolean bStop = false;
    private boolean lStop = false;
    private boolean rStop = false;

    public Bomb(DConsole dc) {
        this.dc = dc;
    }

    public void draw(Map m, Player p) {

        dc.setOrigin(DConsole.ORIGIN_CENTER);
        dc.drawImage("Bomb.png", x, y);//draw it
        delay++;//start timer
        m.grid[yPos + 1][xPos + 1] = BrickID.BOMB;

    }

    public void explode(Map m, Player p) {
        for (int row = 0; row < m.grid.length; row++) {
            for (int col = 0; col < m.grid[0].length; col++) {
                for (int width = 0; width < size + 1; width++) {

                    if (m.grid[p.yPos1 + 1][p.xPos1 + 1] == BrickID.BOMB) {
                        p.hit1 = true;
                    }
                    if (m.grid[p.yPos2 + 1][p.xPos2 + 1] == BrickID.BOMB) {
                        p.hit2 = true;
                    }

                    if (!rStop) {
                        if (m.grid[yPos + 1][xPos + 1 + width] == BrickID.UNBREAKABLE
                                || m.grid[yPos + 1][xPos + 1 + width] == BrickID.BREAKABLE) {// if hits tile, stop expansion
                            rStop = true;
                        }
                        if (m.grid[yPos + 1][xPos + 1 + width] == BrickID.PLAYER1) {//if it hits player 1
                            p.hit1 = true;
                        }
                        if (m.grid[yPos + 1][xPos + 1 + width] == BrickID.PLAYER2) {//if it hits player 2
                            p.hit2 = true;
                        }
                        if (m.grid[yPos + 1][xPos + 1 + width] != BrickID.UNBREAKABLE) {
                            if (m.grid[yPos + 1][xPos + 1 + width] == BrickID.BREAKABLE) {
                                m.grid[yPos + 1][xPos + 1 + width] = BrickID.BROKEN;
                            } else {
                                m.grid[yPos + 1][xPos + 1 + width] = BrickID.EXPLODING;//explosion animation
                            }
                            m.duration = 3000;
                        }
                    }

                    if (!lStop) {
                        if (m.grid[yPos + 1][xPos + 1 - width] == BrickID.UNBREAKABLE
                                || m.grid[yPos + 1][xPos + 1 - width] == BrickID.BREAKABLE) {// if hits tile, stop expansion
                            lStop = true;
                        }
                        if (m.grid[yPos + 1][xPos + 1 - width] == BrickID.PLAYER1) {//if it hits player 1
                            p.hit1 = true;
                        }
                        if (m.grid[yPos + 1][xPos + 1 - width] == BrickID.PLAYER2) {//if it hits player 2
                            p.hit2 = true;
                        }
                        if (m.grid[yPos + 1][xPos + 1 - width] != BrickID.UNBREAKABLE) {
                            if (m.grid[yPos + 1][xPos + 1 - width] == BrickID.BREAKABLE) {
                                m.grid[yPos + 1][xPos + 1 - width] = BrickID.BROKEN;
                            } else {
                                m.grid[yPos + 1][xPos + 1 - width] = BrickID.EXPLODING;//explosion animation
                            }
                            m.duration = 3000;
                        }
                    }

                    if (!tStop) {
                        if (m.grid[yPos + 1 - width][xPos + 1] == BrickID.UNBREAKABLE
                                || m.grid[yPos + 1 - width][xPos + 1] == BrickID.BREAKABLE) {
                            tStop = true;
                        }
                        if (m.grid[yPos + 1 - width][xPos + 1] == BrickID.PLAYER1) {//if it hits player 1
                            p.hit1 = true;
                        }
                        if (m.grid[yPos + 1 - width][xPos + 1] == BrickID.PLAYER2) {//if it hits player 2
                            p.hit2 = true;
                        }
                        if (m.grid[yPos + 1 - width][xPos + 1] != BrickID.UNBREAKABLE) {
                            if (m.grid[yPos + 1 - width][xPos + 1] == BrickID.BREAKABLE) {
                                m.grid[yPos + 1 - width][xPos + 1] = BrickID.BROKEN;
                            } else {
                                m.grid[yPos + 1 - width][xPos + 1] = BrickID.EXPLODING;//explosion animation
                            }
                            m.duration = 3000;
                        }
                    }

                    if (!bStop) {

                        if (m.grid[yPos + 1 + width][xPos + 1] == BrickID.UNBREAKABLE
                                || m.grid[yPos + 1 + width][xPos + 1] == BrickID.BREAKABLE) {// if hits unbreakable tile, stop expansion
                            bStop = true;
                        }
                        if (m.grid[yPos + 1 + width][xPos + 1] == BrickID.PLAYER1) {//if it hits player 1
                            p.hit1 = true;
                        }
                        if (m.grid[yPos + 1 + width][xPos + 1] == BrickID.PLAYER2) {//if it hits player 2
                            p.hit2 = true;
                        }
                        if (m.grid[yPos + 1 + width][xPos + 1] != BrickID.UNBREAKABLE) {
                            if (m.grid[yPos + 1 + width][xPos + 1] == BrickID.BREAKABLE) {
                                m.grid[yPos + 1 + width][xPos + 1] = BrickID.BROKEN;
                            } else {
                                m.grid[yPos + 1 + width][xPos + 1] = BrickID.EXPLODING;//explosion animation
                            }
                            m.duration = 3000;
                        }
                    }
                }
            }
        }
        tStop = false;
        bStop = false;
        lStop = false;
        rStop = false;
        isPlaced = false;
        delay = 0;

        if (placedBy == 1) {
            p.nextBomb1--;
        }
        if (placedBy == 2) {
            p.nextBomb2--;
        }
    }

    public void check(Player p, Map m) {

        if (this.delay >= 200
                && this.isPlaced) {// if the bomb is supposed to explode
            explode(m, p);
        }

        if (m.duration >= 0) {
            m.duration--;
        }

        // place bomb if capable
        if (dc.isKeyPressed(' ')
                && p.nextBomb1 < p.maxBomb1 //if you have bombs to place
                && !isPlaced // if it isn't placed
                && p.xPos1 == this.xPos//only place at player position
                && p.yPos1 == this.yPos) {

            this.size = p.size1;//apply powerups
            isPlaced = true; // place bomb at player position
            p.nextBomb1++; //add 1 to placed bombs; you can't have more than maxBomb
            placedBy = 1;//player 1 placed it

        }

        if (dc.isKeyPressed(16) //shift
                && p.nextBomb2 < p.maxBomb2//if you have bombs to place
                && !isPlaced // if it isn't placed
                && p.xPos2 == this.xPos//only place at player position
                && p.yPos2 == this.yPos) {

            this.size = p.size2;//apply powerups
            isPlaced = true; // place bomb at player position
            p.nextBomb2++;//add 1 to placed bombs; you can't have more than maxBomb
            placedBy = 2;//player 2 placed it

        }
    }
}
