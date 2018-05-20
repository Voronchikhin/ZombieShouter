package NEOfr.GameLogic.Unit;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Unit {
    protected int width;
    protected int height;

    public int getPos(){
        return  xPos;
    }

    public Unit (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isAlive = true;
        this.health = DEFAULT_HEALTH;
        this.height = DEFAULT_HEIGHT;
        this.width = DEFAULT_WIDTH;
    }
    public void doAction(){

    }
    public boolean isTouch(int x, int y){
        if( x < xPos+width && x > xPos &&
                y < yPos && y > yPos - height)
            return true;
        return false;
    }
    public boolean isTouch(int startX, int startY, int dstX, int dstY   ){
        double k = 0;  //find line y = kx+b
        double b = 0;
        k = (dstY - startY) / (dstX - dstY);
        b = dstY - k*dstX;

        if( (k*xPos + b) < yPos && (k*xPos + b) >= yPos - height ){
            return true;
        }
        return false;

    }



    public boolean getDamage(int damage){
        this.health -= damage;
        if( this.health <= 0 ){
            die();
            return true;
        }
        return false;
    }
    public boolean isAlive(){
        return isAlive;
    }
    static public int DEFAULT_WIDTH = 25;
    static public int DEFAULT_HEIGHT = 50;
    static public int DEFAULT_HEALTH = 100;

    protected void die(){
        health = 0;
        isAlive = false;
    }
    protected int xPos;
    protected int yPos;
    private boolean isAlive;
    protected int health;

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
