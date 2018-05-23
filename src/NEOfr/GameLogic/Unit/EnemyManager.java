package NEOfr.GameLogic.Unit;

import java.util.LinkedList;
import java.util.List;

public abstract class EnemyManager {
    public List<Enemy> getWave(int waveNumber, int complexity, int width) {
        List<Enemy> result = new LinkedList<>();
        for( int i = 0; i < 5; ++i ){
            ((LinkedList<Enemy>) result).addLast(new Enemy(i*100+width, 500));
        }
        return result;
    }
}
