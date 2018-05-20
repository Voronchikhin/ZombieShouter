package NEOfr.GameLogic.View;

import NEOfr.GameLogic.Level.Action;
import NEOfr.GameLogic.Level.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.LinkedList;
import java.util.List;


public class View extends JPanel {



    public View() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playerAction.y = e.getY();
                playerAction.x = e.getX();
                playerAction.isShot = true;
            }
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }
        });
    }


    private List<ViewElement> activeElements = new LinkedList<>();
    private List<ViewElement> staticElements = new LinkedList<>();

    private Image backround = new ImageIcon(View.class.getResource("background.png")).getImage();
    private JFrame frame = new JFrame();
    private ActionListener listener = new ActionListener(){
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
        frame.getGraphics().drawImage(backround,0,0,1000,500,null);
        for( ViewElement view : activeElements ){
            frame.getGraphics().drawImage(view.sprite, view.x,view.y,view.width,view.height,null);
        }
        for( ViewElement view : staticElements  ){
            frame.getGraphics().drawImage(view.sprite, view.x,view.y,view.width,view.height,null);
        }
        long end = System.currentTimeMillis();
        if( end - start > 3 ){
            System.out.println("uhhhh blya freezem " + (end - start) );
        }
    }

    public ActionListener getListener() {
        return listener;
    }
}
