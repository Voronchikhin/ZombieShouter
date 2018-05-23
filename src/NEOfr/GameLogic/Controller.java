package NEOfr.GameLogic;

import NEOfr.GameLogic.Level.Level;
import NEOfr.GameLogic.Level.LevelObserver;
import NEOfr.GameLogic.Level.Player;
import NEOfr.GameLogic.Unit.Unit;
import NEOfr.GameLogic.View.View;
import NEOfr.GameLogic.View.ViewElement;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private View view = new View(1200,600);
    private Level game = new Level(1, view.getListener(), new LevelObserver() {
        @Override
        public void updatePlayer(Player playerr) {
            player = playerr;
            view.setPlayer(player);
        }

        @Override
        public void updateStaticObjects(List<? extends Unit> staticObjects) {
            stat.clear();
            for( Unit unit : staticObjects){
                stat.add(new ViewElement("background.png",unit.getWidth(),unit.getHeight(), unit.getPos(), unit.getY() - unit.getHeight()));
            }
        }

        @Override
        public void updateActiveObjects(List<? extends Unit> activeObjects) {
            dyn.clear();
            for( Unit unit : activeObjects ){
                dyn.add(new ViewElement("zombie.png",unit.getWidth(),unit.getHeight(), unit.getPos(), unit.getY() - unit.getHeight()));
            }
        }

        @Override
        public void endGame(boolean isEnded) {
            timer.cancel();

        }
    });
    Timer timer = new Timer();
    private boolean started;
    private Player player;
    private List<ViewElement> stat = new LinkedList<>();
    private List<ViewElement> dyn = new LinkedList<>();
    public Controller() {
        started = true;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if( started ){
                    long start = System.currentTimeMillis();
                    game.tick();
                    long end = System.currentTimeMillis();
                    start = System.currentTimeMillis();
                    view.setActiveObjects(dyn);
                    view.setStaticObjects(stat);
                    end = System.currentTimeMillis();
                    SwingUtilities.invokeLater(() -> view.draw());
                }
            }
        },0,50);
    }
}
