package NEOfr.GameLogic.Unit;

public class Barrier extends Unit {
    public Barrier(int xPos, int yPos) {
        super(xPos, yPos);
        super.health *= 3;
        super.height = 300;
        super.width = 150;
        logger.info("created barrier at(xPos,yPos)");
    }
}
