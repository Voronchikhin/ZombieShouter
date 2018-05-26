package NEOfr.GameLogic.Unit;

public class Boss extends Enemy {
    public Boss(int x, int y) {
        super(x,y);
        super.height *=2;
        super.width *=2;
        super.health *=4;
        super.id = "boss";
        super.headX1 = xPos;
        super.headX2 = xPos+width;
        super.headY1 = yPos - 3*height/4;
        super.headY2 = yPos-height;
    }
}
