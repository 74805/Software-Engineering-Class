package Exe3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Game {
    private JFrame frame;
    private Board board;

    public Game() {
        board = new Board();
        this.frame = new JFrame("Life Engine");
    }

    public void play() {

    }

    public void display() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        JPanel lifePanel = new JPanel();
        mainPanel.add(lifePanel, BorderLayout.CENTER);

        mainPanel.setPreferredSize(new Dimension(800, 500));
        lifePanel.setPreferredSize(new Dimension(800, 400));

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        board.display(frame);
    }

}
