package NEOfr.GameLogic.Level;

import NEOfr.GameLogic.Unit.Unit;

import java.util.List;

public interface LevelObserver {
    void updateStaticObjects(List<? extends Unit> staticObjects);
    void updateActiveObjects(List<? extends Unit> activeObjects);
    void updateScore(int score);
    void updatePlayer(Player player);
    void endGame(boolean isEnded);
}
