package cellular.automata;

import DLibX.DConsole;
import java.awt.Color;
import java.io.IOException;
import java.util.*;

public class CellularAutomata {

    public static void main(String[] args) throws IOException {

        DConsole dc = new DConsole(1300, 660);

        ArrayList<Cell> c = new ArrayList<>();

        dc.setResizable(false);

        Color water = new Color(36, 131, 248);
        Color land = new Color(17, 138, 43);
        Random rand = new Random();

        int worldAge = 0;
        int totalStrength = 0;
        short toPlace = 4;
        List<Cell.Team> team = Arrays.asList(Cell.Team.RED, Cell.Team.BLUE, Cell.Team.CYAN,
        Cell.Team.PINK, Cell.Team.YELLOW, Cell.Team.BLACK,Cell.Team.WHITE,
        Cell.Team.MAGENTA);

        dc.setVisible(false);

        dc.setOrigin(DConsole.ORIGIN_CENTER);

        dc.drawImage("world maps.png", dc.getWidth() / 2, dc.getHeight() / 2);
        dc.setBackground(water);

        dc.redraw();

        for (int i = 0; i < toPlace; i++) {

            short x = (short) rand.nextInt(dc.getWidth());
            short y = (short) rand.nextInt(dc.getHeight());

            if (dc.getPixelColor(x, y).equals(land)) {
                Cell.Team t = team.get(toPlace - i - 1);
                addColony(dc, c, t, x, y);
            } else {
                toPlace++;
            }
        }
        dc.setVisible(true);

        while (true) {

            dc.clear();

            dc.setOrigin(DConsole.ORIGIN_CENTER);
            dc.drawImage("world maps.png", dc.getWidth() / 2, dc.getHeight() / 2);

            totalStrength = 0;

            for (int i = 0; i < c.size(); i++) {

                totalStrength += c.get(i).fitness;

                c.get(i).draw();
                c.get(i).move();
                c.get(i).grow();

                for (int q = 0; q < c.size(); q++) {//interacting with all other cells
                    if (c.get(i).x == c.get(q).x
                            && c.get(i).y == c.get(q).y
                            && c.get(i).team != c.get(q).team) {
                        c.get(i).damageTaken++;
                    }
                }

                if (c.get(i).canReproduce()) {//reproduce
                    Cell.Team t = c.get(i).team;
                    short s = c.get(i).baseStrength;
                    short x = c.get(i).x;
                    short y = c.get(i).y;
                    c.add(new Cell(dc, t, s, x, y));
                }

                if (c.get(i).canDie()//check if they're supposed to die
                        || c.get(i).drowning()) {//or if they're alone far off shore
                    c.remove(i);//'kill' them
                }
            }

            dc.setPaint(Color.BLACK);
            dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
            dc.drawString(totalStrength + ", " + c.size() + ", " + (double) (totalStrength) / (double) (c.size()), 20, 20);
            dc.drawString(worldAge, 20, 50);
            worldAge++;
            
            dc.pause(50);
            dc.redraw();
        }
    }

    public static void addColony(DConsole dc, List c, Cell.Team t, short x, short y) throws IOException {

        for (int i = 0; i < 50; i++) { //i counts upto cells per colony
            c.add(new Cell(dc, t, 10, x, y));
        }
    }
}
