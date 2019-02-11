/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watersim;

/**
 *
 * @author Cole
 */
public class Coordinate {
    private double x;
    private double y;
    
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public void changeX(double change){
        this.x += change;
    }
    
    public void changeY(double change){
        this.y += change;
    }
}
