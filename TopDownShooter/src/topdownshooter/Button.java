package topdownshooter;

import DLibX.DConsole;

public class Button {

    private DConsole dc;
    int x;
    int y;
    int width;
    int height;
    String content;//what's inside the box

    public Button(DConsole dc, int x, int y, int w, int h, String c) {

        this.dc = dc;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.content = c;

    }

    public boolean clicked() {
        int mouseX = dc.getMouseXPosition();
        int mouseY = dc.getMouseYPosition();
        
        //if the mouse is within the box, return true. else, return false
        return mouseX > this.x - this.width
                && mouseX < this.x + this.width
                && mouseY > this.y - this.height
                && mouseY < this.y + this.height
                && dc.isMouseButton(1);

    }

    public void draw() {
        dc.setOrigin(DConsole.ORIGIN_CENTER);
        dc.drawRect(x, y + 20, width, height);
        dc.drawString(content, x, y);
    }

}
