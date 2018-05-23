package NEOfr.GameLogic.Unit;


import org.apache.log4j.LogManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Unit {
    protected int width;
    protected int height;

    public int getPos(){
        return  xPos;
    }

    public Unit (int xPos, int yPos) {
        this.height = DEFAULT_HEIGHT;
        this.width  = DEFAULT_WIDTH;
        this.health = DEFAULT_HEALTH;

        this.xPos = xPos;
        this.yPos = yPos;

        this.headY1 = yPos - this.height;
        this.headY2 = yPos;

        this.headX1 = this.xPos;
        this.headX2 = this.headX1 + this.width;

        this.isAlive = true;
    }

    public boolean getShot(int startX, int startY, int dstX, int dstY, int damage){
        int damage_ = 0;
        boolean isShoted = false;
        double k;
        double b;

        k = (double) (dstY - startY)/(dstX - startX);
        b = dstY - k*dstX;

        if( (b + k*xPos)<=yPos && (b + k*(xPos+width) )>=(yPos - height)  ){
            damage_ += damage;
            isShoted = true;
            System.out.println("shootted ");
        }

        if( (b + k*headX1)<=headY1 && (b + k*(headX2) )>=headY2  ){
            damage_ += 2*damage;
            isShoted = true;
            System.out.println("shootted in head");
        }

        getDamage(damage_, this);

        return isShoted;
    }


    public boolean getDamage(int damage, Unit asker){
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
        logger.debug("die");
        health = 0;
        isAlive = false;
    }
    protected int xPos;

    protected int headX1;
    protected int headX2;
    protected int headY1;
    protected int headY2;

    protected int yPos;
    private boolean isAlive;
    protected int health;
    protected org.apache.log4j.Logger logger = LogManager.getLogger(Unit.class);

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
