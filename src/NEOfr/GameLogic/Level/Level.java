package NEOfr.GameLogic.Level;

import NEOfr.GameLogic.Unit.Enemy;
import NEOfr.GameLogic.Unit.EnemyManager;
import NEOfr.GameLogic.Unit.Unit;


import java.util.LinkedList;
import java.util.List;

public class Level {


    public Level(int complexity, ActionListener playerActionListener, LevelObserver levelObserver) {
        logger.
        this.complexity = complexity;
        this.playerActionListener = playerActionListener;
        this.levelObserver = levelObserver;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        waveNumber = 0;
        isEnded = false;
        units = new LinkedList<>();
        logger.debug("level created");
        this.enemyManager = new EnemyManager() {
            @Override
            public List<Enemy> getWave(int waveNumber, int complexity) {
                return super.getWave(waveNumber, complexity);
            }
        };
        enemies = this.enemyManager.getWave(this.waveNumber, this.complexity);
    }

    public static int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public static int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    public void tick(){
        logger.debug("tick started");
        action = playerActionListener.getAction();
        isEdited = changeWorld();
        if(isEdited){
            logger.debug("static objects has changed");
            levelObserver.updateStaticObjects(units);
            isEdited = false;
        }
        levelObserver.updateActiveObjects(enemies);
        logger.debug("tick ended");
    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getComplexity() {
        return complexity;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public LevelObserver getLevelObserver() {
        return levelObserver;
    }

    private boolean changeWorld(){
        if (action.isShot){
            shotHandle(action.x, action.y);
        }

        if( action.isAction ){
            actionHandle(action.x, action.y);
        }

        for (Enemy enemy : enemies) {
            if (enemy.attack() == true ){
                isEdited = true;
            }
        }

        enemies.removeIf(enemy -> {return (enemy.isAlive()==false);});

        return false;
    }


    public static int DEFAULT_WIDTH = 1200;
    public static int DEFAULT_HEIGHT = 1200;

    private boolean isEdited = true;
    private boolean isEnded;
    private int width;
    private int height;

    private void actionHandle(int x, int y){

    }

    private void shotHandle(int x, int y){

    }

    private List<Unit> units;
    private List<Enemy> enemies;
    private Action action;
    private int complexity;
    private int waveNumber;
    private EnemyManager enemyManager;
    static org.apache.log4j.Logger logger = org.apache.log4j.LogManager.getLogger(Level.class);
    private ActionListener playerActionListener;
    private LevelObserver levelObserver;
}
