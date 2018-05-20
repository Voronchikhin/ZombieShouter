package NEOfr.GameLogic.Unit;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import NEOfr.GameLogic.Unit.Barrier;

public class Enemy extends Unit {
    public Enemy(int xPos, int yPos) {
        super(xPos, yPos);
        this.velocity = DEFAULT_VELOCITY;
        logger.info("created Enemy at(xPos,yPos)");
        width = 100;
        height = 200;
    }

    public boolean attack(){
        /*for( Barrier barrier : barriers){
            if( barrier.isTouch(xPos + velocity,yPos) ){
                barrier.getDamage(damage);
                logger.info("attack barrier");
                return true;
            }
        }*/
        //logger.info("moving");
        xPos += velocity;
        if (xPos <= 0){
            logger.fine("kilayu suku");
        }
        return false;
    }

    static public int DEFAULT_VELOCITY = -5;
    protected int velocity;
    protected int damage = 15;
}
