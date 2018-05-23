package NEOfr.GameLogic.Unit;

import java.awt.*;
import java.util.List;

import NEOfr.GameLogic.Unit.Barrier;

public class Enemy extends Unit {
    public Enemy(int xPos, int yPos) {
        super(xPos, yPos);
        this.velocity = DEFAULT_VELOCITY;
        logger.info("created Enemy ");
        super.width = 100;
        super.height = 200;

        super.headX1 = xPos;
        super.headX2 = xPos+width;
        super.headY1 = yPos - 3*height/4;
        super.headY2 = yPos-height;
    }

    public boolean attack(List<?extends Unit> barriers){
        boolean changeEnvp = false;
        logger.trace("moving");
        for( Unit unit: barriers ){
            if( unit.xPos+unit.width >= xPos+velocity ){
                logger.trace("atack block");
                changeEnvp = true;
                unit.getDamage(this.damage,this);
                return changeEnvp;
            }
        }
        xPos += velocity;
        headX1 +=velocity;
        headX2 +=velocity;
        if (xPos <= 0){
            velocity = 0;
            logger.debug("killing player");
        }
        return changeEnvp;
    }

    static public int DEFAULT_VELOCITY = -2;
    protected int velocity;
    protected int damage = 15;
}
