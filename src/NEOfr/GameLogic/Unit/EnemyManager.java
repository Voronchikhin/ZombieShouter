package NEOfr.GameLogic.Unit;

import java.util.LinkedList;
import java.util.List;

public abstract class EnemyManager {
    public List<Enemy> getWave(int waveNumber, int complexity, int width) {
        List<Enemy> result = new LinkedList<>();
        for( int i = 0; i < 5+waveNumber; ++i ){
            ((LinkedList<Enemy>) result).addLast(new Enemy(i*100+width, 500));
            ((LinkedList<Enemy>) result).getLast().setVelocity(((LinkedList<Enemy>) result).getLast().velocity - waveNumber / 2);
        }
        if (waveNumber > 2) {
            ((LinkedList<Enemy>) result).addLast(new Boss(waveNumber*5+width,500));
        }
        return result;
    }
}
