package NEOfr.GameLogic.View;

import NEOfr.GameLogic.Level.Action;
import NEOfr.GameLogic.Level.ActionListener;
import NEOfr.GameLogic.Level.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.LinkedList;
import java.util.List;


public class View extends JPanel {

    public View(int width, int height) {
        this.height = height;
        this.width = width;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playerAction.y = e.getY();
                playerAction.x = e.getX();
                playerAction.isShot = true;
                playerAction.isAction = false;
                if( e.getButton()==3 ){
                    playerAction.isAction = true;
                    playerAction.isShot = false;
                }
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
    }


    private List<ViewElement> activeElements = new LinkedList<>();
    private List<ViewElement> staticElements = new LinkedList<>();

    private Player player;
    private int mouseX;
    private int mouseY;

    private int width;
    private int height;

    private int bulletCount;

    private Image playerImg = new ImageIcon(View.class.getResource("player.png")).getImage();
    private Image backround = new ImageIcon(View.class.getResource("background.png")).getImage();
    private JFrame frame = new JFrame();
    private ActionListener listener = new ActionListener(){

        @Override
        public boolean setAction(Action action) {
            playerAction = action;
            return true;
        }

        @Override
        public Action getAction() {
            return playerAction;
        }
    };

    private Action playerAction = new Action();



    public void setActiveObjects(List<ViewElement> dyn) {
        activeElements = dyn;
    }

    public void setStaticObjects(List<ViewElement> stat) {
        staticElements = stat;
    }

    public void draw() {
        long start = System.currentTimeMillis();
        frame.getGraphics().drawImage(backround,0,0,width,height,null);
        frame.getGraphics().drawImage(playerImg,player.getxPos(),player.getyPos(),player.getWidth(),player.getHeight(),null);
        for( ViewElement view : activeElements ){
            frame.getGraphics().drawImage(view.sprite, view.x,view.y,view.width,view.height,null);
        }
        for( ViewElement view : staticElements  ){
            frame.getGraphics().drawImage(view.sprite, view.x,view.y,view.width,view.height,null);
        }
        Graphics frame_ = frame.getGraphics();
        frame_.setColor(Color.RED);
        frame_.drawLine(player.getRifleX()+player.getxPos(), player.getRifleY()+player.getyPos(), mouseX, mouseY);
        frame_.drawString(String.valueOf(player.getBulletCount()),500,50);
        long end = System.currentTimeMillis();
        if( end - start > 3 ){
            System.out.println("uhhhh blya freezem " + (end - start) );
        }
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
