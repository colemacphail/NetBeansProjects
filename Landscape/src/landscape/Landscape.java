package landscape;

import DLibX.DConsole;
import java.awt.*;
import java.util.*;

public class Landscape {

    public static void main(String[] args) {

        DConsole dc = new DConsole();

        dc.setOrigin(DConsole.ORIGIN_CENTER);
        //sky
        dc.setPaint(new Color(96, 153, 219));
        dc.fillRect(450, 300, 1000, 1000);

        //ground
        dc.setPaint(new Color(102, 232, 110));
        dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
        dc.fillPolygon(new int[]{-100, -100, 1000, 1000}, new int[]{100, 700, 700, 200});

        //barn
        dc.setPaint(new Color(85,66,58));
        dc.fillPolygon(new int[] {250, 137, 150}, new int[] {151, 151, 250}); //side of roof
        dc.fillArc(47, 149, 220, 220, 90, 80);
        dc.setPaint(new Color(130, 60, 60));
        dc.fillPolygon (new int[] {150, 150, 50, 50}, new int[] {250, 350, 325, 225});//side of barn
        dc.setPaint(new Color(255, 81, 38));
        dc.setOrigin(DConsole.ORIGIN_CENTER);
        dc.fillRect(250, 300, 200, 100); //front
        dc.fillArc(250, 200, 200, 200, 0, 180); //front top arch
        
        
        dc.redraw();
    }
}
