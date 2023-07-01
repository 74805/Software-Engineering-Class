package Exe3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Game {
    private JFrame frame;
    private Board board;
    private JPanel boardPanel;

    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    private ArrayList<JButton> editButtons;

    private final int rows = 30;
    private final int cols = 40;

    public Game() {
        board = new Board(rows, cols);
    }

    // Start/Resume the game
    private void play() {
        Thread thread = new Thread(() -> {
            // Disable the board buttons
            board.disable();

            // Disable the start button
            startButton.setEnabled(false);

            while (true) {
                board.update();
                board.updateUI();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    // Pause the game
    public void stop() {

    }

    // Reset the game
    public void reset() {

    }

    public void display() {
        frame = new JFrame("the Life engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel(new GridLayout(rows, cols));
        frame.add(boardPanel, BorderLayout.NORTH);

        board.display(boardPanel);

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        // add actions to the buttons
        startButton.addActionListener(e -> play());
        stopButton.addActionListener(e -> stop());
        resetButton.addActionListener(e -> reset());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        JPanel editButtonPanel = new JPanel();
        editButtons = new ArrayList<>();
        editButtons.add(new JButton("Empty"));
        editButtons.add(new JButton("Food"));
        editButtons.add(new JButton("Killer"));

        editButtons.get(0).setBackground(Color.GRAY);
        editButtons.get(1).setBackground(Color.GREEN);
        editButtons.get(2).setBackground(Color.PINK);

        for (JButton button : editButtons) {
            // make the buttons square
            button.setPreferredSize(new Dimension(60, 60));

            // make the text smaller
            button.setFont(button.getFont().deriveFont(8f));

            editButtonPanel.add(button);
        }

        frame.add(editButtonPanel, BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

}
