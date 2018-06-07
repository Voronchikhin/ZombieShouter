package NEOfr.GameLogic.View;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private int score;
    private JFrame frame = new JFrame();
    private JTable tableBoard = new JTable();

    public ScoreBoard(int score) {
        this.score = score;
        setSize(700, 900);
        frame.setSize(700, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TableColumn names = new TableColumn(6, 75);
        TableColumn scores = new TableColumn(6, 75);
        tableBoard.addColumn(names);
        tableBoard.addColumn(scores);
        this.add(tableBoard);
        frame.add(this);
        frame.setVisible(true);
        frame.setResizable(false);
        this.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String mes = new String();
        if (score >= 400)
            mes = "real man ";
        if (score < 400)
            mes = "looser ";
        if (score > 1000)
            mes = mes + "i zadrot ";
        mes = mes + score;
        g.drawString(mes, 100, 100);
        g.drawString("thanks for bug testing : Andrew Kutalev, Ilya Romakhin", 150, 800);
    }
}
