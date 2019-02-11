package mario.party;

import DLibX.DConsole;
import java.util.ArrayList;

/**
 *
 * @author Cole
 */
public class MapGenerator {

    private Map map;
    private final ArrayList<Position> pos;
    private final DConsole dc;

    public MapGenerator(DConsole dc, Map m, ArrayList<Position> p) {
        this.pos = p;
        this.map = m;
        this.dc = dc;
    }

    public void mapConfig() {
        if (this.map == Map.BASIC) {
            for (int i = 0; i < 10; i++) {
                this.pos.add(new Position(this.dc, i * 50, 0));
            }
            for (int i = 10; i < 20; i++) {
                this.pos.add(new Position(this.dc, 500, i * 50));
            }
            for (int i = 20; i < 30; i++) {
                this.pos.add(new Position(this.dc, 500 - i * 50, 500));
            }
            for (int i = 10; i < 20; i++) {
                this.pos.add(new Position(this.dc, 0, 500 - i * 50));
            }
        } else{
            System.out.println("What in tarnation how'd you pick a map that doesn't exist?");
            System.out.println("I'll set it to BASIC");
            this.map = Map.BASIC;
            this.mapConfig();
        }
    }
}
