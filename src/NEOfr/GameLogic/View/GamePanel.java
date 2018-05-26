package NEOfr.GameLogic.View;

import NEOfr.GameLogic.Level.Action;
import NEOfr.GameLogic.Level.ActionListener;
import NEOfr.GameLogic.Level.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.util.LinkedList;
import java.util.List;


public class GamePanel extends JPanel {

    private int score;
    public GamePanel(int width, int height, String playerImage) {
        setLayout(null);
        this.playerImage = playerImage;
        this.playerImg = new ImageIcon(GamePanel.class.getResource(this.playerImage)).getImage();
        this.height = height;
        this.width = width;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        this.addMouseListener(new MouseAdapter() {
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
        this.addMouseMotionListener(new MouseMotionAdapter() {
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
    private String playerImage;


    private Image playerImg ;
    private Image background = new ImageIcon(GamePanel.class.getResource("background.png")).getImage();
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


    private boolean menuMode = true;

    public void setActiveObjects(List<ViewElement> dyn) {
        activeElements = dyn;
    }

    public void setStaticObjects(List<ViewElement> stat) {
        staticElements = stat;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,width,height,null);
        g.drawImage(playerImg,player.getxPos(),player.getyPos(),player.getWidth(),player.getHeight(),null);
        for( ViewElement view : activeElements ){
            g.drawImage(view.sprite, view.x,view.y,view.width,view.height,null);
        }
        for( ViewElement view : staticElements  ){
            g.drawImage(view.sprite, view.x,view.y,view.width,view.height,null);
        }

        g.setColor(Color.RED);
        g.drawLine(player.getRifleX()+player.getxPos(), player.getRifleY()+player.getyPos(), mouseX, mouseY);
        g.drawString("bullets: "+String.valueOf(player.getBulletCount()),500,50);
        g.drawString("Score :"+String.valueOf(score), 500,100);

    }

    public void draw() {
        this.repaint();
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
