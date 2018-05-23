package NEOfr.GameLogic.Level;

public class Action {
    public boolean isShot;
    public boolean isAction;
    public int x;
    public int y;

    public Action(){

    }
    public Action(int x, int y) {
        this.x = x;
        this.y = y;
        isAction = false;
        isShot = false;
    }
}
