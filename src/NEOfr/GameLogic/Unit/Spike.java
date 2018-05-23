package NEOfr.GameLogic.Unit;

public class Spike extends Barrier {
    public Spike(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public boolean getDamage(int damage, Unit asker) {
        if (null!=asker){
            asker.getDamage(this.damage, this);
        }
        return super.getDamage(damage, asker);
    }
    private int damage = 35;
}
