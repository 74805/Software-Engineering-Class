package Exe3;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Game {
    private JFrame frame;
    private Board board;

    public Game() {
        board = new Board();
        this.frame = new JFrame("Life Engine");

        display();
    }

    public void play() {
        // Disable the board buttons
        board.disable();

        // Start the game
        // while (true) {
        // board.update();
        // try {
        // Thread.sleep(100);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
    }

    public void display() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(mainPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        constraints.weightx = 1.0; // Take up available horizontal space
        constraints.weighty = 1.0; // Take up available vertical space

        // cellPanel
        JPanel cellPanel = new JPanel();
        constraints.gridy = 0;
        constraints.weighty = 0.6;
        mainPanel.add(cellPanel, constraints);

        // buttonPanel
        JPanel buttonPanel = new JPanel();
        constraints.gridy = 1;
        constraints.weighty = 0.035;
        mainPanel.add(buttonPanel, constraints);

        // cellOptions
        JPanel cellOptions = new JPanel();
        constraints.gridy = 2; // Position below cellPanel
        constraints.weighty = 0.08; // Occupy no additional vertical space
        mainPanel.add(cellOptions, constraints);

        // mainPanel.setPreferredSize(new Dimension(800, 800));
        // buttonPanel.setPreferredSize(new Dimension(800, 800));
        // cellPanel.setPreferredSize(new Dimension(800, 500));
        // cellOptions.setPreferredSize(new Dimension(800, 800));

        // Create button panel buttons
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        // Create cell options buttons
        Dimension buttonSize = new Dimension(70, 70);
        Font buttonFont = new Font("Arial", Font.PLAIN, 12);

        JButton emptyButton = new JButton("Empty");
        JButton foodButton = new JButton("Food");
        emptyButton.setFont(buttonFont);
        foodButton.setFont(buttonFont);
        emptyButton.setPreferredSize(buttonSize);
        foodButton.setPreferredSize(buttonSize);

        cellOptions.add(emptyButton);
        cellOptions.add(foodButton);

        frame.setResizable(false);

        board.display(cellPanel);
        frame.setVisible(true);
    }

}
