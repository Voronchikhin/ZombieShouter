package NEOfr.GameLogic.Level;

public class ActionListener {
    public boolean setAction(Action action){
        this.action = action;
        return true;
    }
    public Action getAction(){
        return action;
    }
    private Action action;
}
