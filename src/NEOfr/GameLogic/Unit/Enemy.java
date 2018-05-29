package NEOfr.GameLogic.Unit;

import java.util.List;
import java.util.Random;

public class Enemy extends Unit {
    public Enemy(int xPos, int yPos) {
        super(xPos, yPos);
        this.velocity = DEFAULT_VELOCITY;
        logger.info("created Enemy ");
        super.width = 100;
        super.height = 200;
        super.id = "enemy";
        super.headX1 = xPos;
        super.headX2 = xPos+width;
        super.headY1 = yPos - 3*height/4;
        super.headY2 = yPos-height;
    }

    static public int DEFAULT_VELOCITY = -5;

    public boolean attack(List<?extends Unit> barriers){
        boolean changeEnvp = false;
        logger.trace("moving");
        for( Unit unit: barriers ){
            if( unit.xPos+unit.width >= xPos+velocity && unit.xPos< xPos+velocity){
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
    private boolean canRampage = true;

    @Override
    protected void die() {
        if (new Random().nextInt(4) >= 3 && canRampage) {
            logger.info("get critical damage  rampage");
            velocity /= 2;
            velocity++;
            id = "crowlyEnemy";
            super.width /= 2;
            super.height /= 2;
            canRampage = false;
            super.headX1 = xPos;
            super.headX2 = xPos + super.width;
            super.headY1 = yPos - 3 * super.height / 4;
            super.headY2 = yPos - super.height;
            return;
        }
        super.die();
    }
    protected int velocity;
    protected int damage = 15;
}
