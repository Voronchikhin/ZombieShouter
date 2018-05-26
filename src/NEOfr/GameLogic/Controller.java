package NEOfr.GameLogic;

import NEOfr.GameLogic.Level.Level;
import NEOfr.GameLogic.Level.LevelObserver;
import NEOfr.GameLogic.Level.Player;
import NEOfr.GameLogic.Unit.Unit;
import NEOfr.GameLogic.View.GamePanel;
import NEOfr.GameLogic.View.MenuPanel;
import NEOfr.GameLogic.View.ViewElement;
import NEOfr.GameLogic.View.ViewFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    Timer timer = new Timer();
    private boolean started = false;
    private Player player;
    private Level game;
    private List<ViewElement> stat = new LinkedList<>();
    private List<ViewElement> dyn = new LinkedList<>();
    private LevelObserver levelObserver = new LevelObserver() {
        @Override
        public void updateScore(int score) {
            gamePanel.setScore(score);
        }

        @Override
        public void updatePlayer(Player playerr) {
            player = playerr;
            gamePanel.setPlayer(player);
        }

        @Override
        public void updateStaticObjects(List<? extends Unit> staticObjects) {
            stat.clear();
            for( Unit unit : staticObjects){
                stat.add(viewFactory.getViewElement(unit));
            }
        }

        @Override
        public void updateActiveObjects(List<? extends Unit> activeObjects) {
            dyn.clear();
            for( Unit unit : activeObjects ){
                dyn.add(viewFactory.getViewElement(unit));
            }
        }

        @Override
        public void endGame(boolean isEnded) {
            started = false;

        }
    };

    public void setConfigName(String configName) {
        this.configName = configName;
    }



    private ViewFactory viewFactory;
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    private String configName;          // uses to init view item factory
    private String levelName;           // using to create level
    private String playerName;          // uses to create game view

    private int complexity= 1;
    private void startGame(){
        gamePanel = new GamePanel(1200,600,playerName);
        game = new Level(complexity, gamePanel.getListener(), levelObserver,levelName);
    }


    public void stopMenu(){
        menuPanel = null;
        viewFactory = new ViewFactory(configName);
        startGame();
        started = true;
    }
    public Controller() {
        //started = true;
        //startGame();
        menuPanel = new MenuPanel(new JFrame(), this);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if( started ){
                    long start = System.currentTimeMillis();
                    game.tick();
                    long end = System.currentTimeMillis();
                    gamePanel.setActiveObjects(dyn);
                    gamePanel.setStaticObjects(stat);
                    SwingUtilities.invokeLater(() -> gamePanel.draw());
                }
            }
        },0,40);
    }
}
