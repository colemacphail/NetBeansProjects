package divekick;

import DLibX.DConsole;
import java.awt.*;

public class Buttons {

    private DConsole dc;

    double x;
    double y;
    int width = 600;
    int height = 200;
    boolean active = false;
    Button button;

    public Buttons(DConsole dc) {
        this.dc = dc;
    }

    public void draw(Player[] p) {
        int mouseX = dc.getMouseXPosition();
        int mouseY = dc.getMouseYPosition();

        dc.setOrigin(DConsole.ORIGIN_CENTER);

        if (mouseX > x - width / 2
                && mouseX < x + width / 2
                && mouseY > y - height / 2
                && mouseY < y + height / 2) {
            if (button == Button.SETTINGS
                    || button == Button.MULTIPLAYER) {
                dc.setPaint(Color.RED);

                if (dc.isMouseButton(1)) {
                    active = true;
                }
            }
            for (int i = 0; i < p.length; i++) {
                if (dc.isMouseButton(1)) {
                    if (button == Button.CHAR_DIVE) {
                        p[i].character = "DIVE";
                    }
                    if (button == Button.CHAR_KICK) {
                        p[i].character = "KICK";
                    }
                }
            }
        } else {
            dc.setPaint(Color.GRAY);
            active = false;
        }
        dc.fillRect(x, y, width, height);
        if (button == Button.SETTINGS
                || button == Button.MULTIPLAYER) {
            dc.setPaint(Color.WHITE);
            dc.setFont(new Font("Roboto", Font.BOLD, 60));
            dc.drawString(button, x, y - 20);
        }
    }
}
