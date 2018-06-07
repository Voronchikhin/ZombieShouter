package NEOfr.GameLogic.View;

import NEOfr.GameLogic.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

public class MenuPanel extends JPanel implements ActionListener {
    //private JRadioButton Efremov = new JRadioButton("For Ivan Efremov");
    private JRadioButton Andrew = new JRadioButton("mexicans");

    private JFrame frame;
    private JRadioButton Max = new JRadioButton("half life episode 3");
    public MenuPanel(JFrame frame, Controller controller) {
        this.observer = controller;
        this.frame = frame;
        //radios.add(Efremov);
        radios.add(Max);
        radios.add(Andrew);
        startButton.setActionCommand("start");
        //Efremov.setActionCommand("Ivan");
        Max.setActionCommand("Max");
        Andrew.setActionCommand("Andrew");
        startButton.addActionListener(this);
        //Efremov.addActionListener(this);
        Max.addActionListener(this);
        Andrew.addActionListener(this);
        //add(Efremov);
        add(Max);
        add(Andrew);
        add(startButton);

        Andrew.doClick();
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    private JButton startButton = new JButton("click to start");
    private ButtonGroup radios = new ButtonGroup();
    private Controller observer;
    private void stopMenu() {
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("menu.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        observer.setPlayerName(props.getProperty(result+"Player"));
        observer.setLevelName(props.getProperty(result+"Level"));
        observer.setConfigName(props.getProperty(result+"Config"));
        observer.stopMenu();
        this.setEnabled(false);
        frame.setVisible(false);
    }
    private String result;

    @Override
    public void actionPerformed(ActionEvent e) {
        if( !e.getActionCommand().contains("start") ){
            result = e.getActionCommand();
            return;
        }
        stopMenu();
    }

}
