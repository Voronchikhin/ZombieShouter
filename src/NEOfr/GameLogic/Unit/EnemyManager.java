package NEOfr.GameLogic.Unit;

import java.util.LinkedList;
import java.util.List;

public abstract class EnemyManager {
    public List<Enemy> getWave(int waveNumber, int complexity) {
        List<Enemy> result = new LinkedList<>();
        for( int i = 0; i < 5; ++i ){
            ((LinkedList<Enemy>) result).addLast(new Enemy(i*100+700, 500));
        }
        return result;
    }
}
