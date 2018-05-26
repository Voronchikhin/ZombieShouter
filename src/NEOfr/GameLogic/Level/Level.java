package NEOfr.GameLogic.Level;

import NEOfr.GameLogic.Unit.Barrier;
import NEOfr.GameLogic.Unit.Enemy;
import NEOfr.GameLogic.Unit.EnemyManager;
import NEOfr.GameLogic.Unit.Unit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;


import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Level {

    private int endPos = 35;

    public Level(int complexity, ActionListener playerActionListener, LevelObserver levelObserver, String levelName) {
        player = new Player(levelName);
        BasicConfigurator.configure();
        logger.debug("level created");
        this.complexity = complexity;
        this.playerActionListener = playerActionListener;
        this.levelObserver = levelObserver;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        waveNumber = 0;
        isEnded = false;
        units = new LinkedList<>();
        this.enemyManager = new EnemyManager() {
            @Override
            public List<Enemy> getWave(int waveNumber, int complexity, int width) {
                return super.getWave(waveNumber, complexity,width);
            }
        };
        units.add(new Barrier(800,500));
        enemies = this.enemyManager.getWave(this.waveNumber, this.complexity,width);
    }

    public static int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public static int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    public void tick(){
        levelObserver.updateScore(this.score);
        if(isEnded){
            logger.info("game ends");
            levelObserver.endGame(false);
            return;
        }
        if(waveNumber > 8){
            logger.info("player win");
            levelObserver.endGame(true);
            return;
        }
        logger.trace("tick started");
        levelObserver.updatePlayer(player);
        action = playerActionListener.getAction();
        playerActionListener.setAction(new Action(0,0));
        isEdited = changeWorld();
        if(isEdited){
            logger.debug("static objects has changed");
            levelObserver.updateStaticObjects(units);
            isEdited = false;
        }
        levelObserver.updateActiveObjects(enemies);
        logger.trace("tick ended");
    }



    public int getWidth() {
        return width;
    }
    private int score = 0;

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
            if(!enemy.isAlive()){
                this.score +=10*(complexity + waveNumber+1);
                continue;
            }
            if (enemy.attack(units)){
                isEdited = true;
            }
            if( enemy.getPos() <= endPos ){
                isEnded = true;
            }
        }

        units.removeIf(unit -> {return (!unit.isAlive());});
        enemies.removeIf(enemy -> {return (!enemy.isAlive()); });
        if( enemies.isEmpty() ){
            enemies = enemyManager.getWave(waveNumber++,complexity,width);
        }
        return isEdited;
    }



    private Player player ;

    public static int DEFAULT_WIDTH = 1200;
    public static int DEFAULT_HEIGHT = 600;

    private boolean isEdited = true;
    private boolean isEnded = false;
    private int width;
    private int height;

    private void actionHandle(int x, int y){
        logger.debug("action handle");
        player.handleAction(x,y,units);
        levelObserver.updateStaticObjects(units);
    }

    private void shotHandle(int x, int y){
        logger.debug("shot handle");
        int bulletPower = player.getBulletPower();
        if( player.getBulletCount() <= 0 ){
            return;
        }
        player.deleteBullet();

        for( Enemy enemy : enemies){
            if(bulletPower <= 0){
                return;
            }
            if( enemy.getShot(player.getRifleX()+player.getxPos(),player.getRifleY()+player.getyPos(),x,y, 15*bulletPower)){
                bulletPower--;
            }
        }

    }




    private List<Unit> units;
    private List<Enemy> enemies;
    private Action action;
    private int complexity;
    private int waveNumber;
    private EnemyManager enemyManager;
    static Logger logger = LogManager.getLogger(Level.class);
    private ActionListener playerActionListener;
    private LevelObserver levelObserver;
}
