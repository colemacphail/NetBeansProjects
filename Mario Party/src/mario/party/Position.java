package mario.party;

import DLibX.DConsole;
import java.awt.Color;

/**
 *
 * @author Cole
 */

public class Position extends Tile{
    
    private Effect effect;
    private DConsole dc;
    private boolean[] isPlayerTouching = new boolean [3];
    private int x;
    private int y;
    
    public Position(DConsole dc, int x, int y){
        this.dc = dc;
        this.x = x;
        this.y = y;
    }
    
    public void setEffect(Effect e){
        this.effect = e;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public void isTouching(int player, boolean isTouching){
        this.isPlayerTouching[player] = isTouching;
    }

    @Override
    void touched() {
        switch(effect){
            case MINIGAME:
                
                break;
            case STAR:
                
                break;
            default: //NONE
                
                break;
            
        }
    }
    
    private void draw(){
        this.dc.setOrigin(DConsole.ORIGIN_CENTER);
        this.dc.setPaint(Color.BLACK);
        this.dc.drawEllipse(this.x, this.y, 40, 40);
    }
    
}
