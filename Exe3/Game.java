package Exe3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Exe3.Cells.EmptyCell;

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
    private Class<? extends Cell> cellType;

    private Thread guiThread;

    private final int rows = 30;
    private final int cols = 40;

    public Game() {
        board = new Board(rows, cols);
    }

    // Start/Resume the game
    private void play() {
        if (guiThread != null && guiThread.isAlive()) {
            // stop the thread
            guiThread.interrupt();

            // wait for the thread to die
            try {
                guiThread.join();
            } catch (InterruptedException e) {
            }
        }

        guiThread = new Thread(() -> {
            // disable the board buttons
            board.disable();

            stopButton.setEnabled(true);
            resetButton.setEnabled(false);
            startButton.setEnabled(false);

            // disable the edit buttons
            for (JButton button : editButtons) {
                button.setEnabled(false);
            }

            while (!guiThread.isInterrupted()) {
                board.update();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        guiThread.start();

    }

    // Pause the game
    public void stop() {
        if (guiThread.isAlive()) {
            // stop the thread
            guiThread.interrupt();

            // wait for the thread to die
            try {
                guiThread.join();
            } catch (InterruptedException e) {
            }
        }

        guiThread = new Thread(() -> {
            // enable the board buttons
            board.enable();

            // enable the edit buttons
            for (JButton button : editButtons) {
                button.setEnabled(true);
            }

            // enable the start button
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            resetButton.setEnabled(true);
        });

        guiThread.start();
    }

    // Reset the game
    public void reset() {

        guiThread = new Thread(() -> {
            // enable the board buttons
            board.reset();

            // enable the edit buttons
            for (JButton button : editButtons) {
                button.setEnabled(true);
            }

            // enable the start button
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            resetButton.setEnabled(false);
        });

        guiThread.start();
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

        stopButton.setEnabled(false);
        resetButton.setEnabled(false);

        // add actions to the buttons
        startButton.addActionListener(e -> play());
        stopButton.addActionListener(e -> stop());
        resetButton.addActionListener(e -> reset());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        JPanel editButtonPanel = new JPanel();
        setEditButtons(editButtonPanel);

        frame.add(editButtonPanel, BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public void setEditButtons(JPanel panel) {
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

            panel.add(button);
        }

        // add actions to the buttons
        editButtons.get(0).addActionListener(e -> setCellType(EmptyCell.class, 0));
        editButtons.get(1).addActionListener(e -> setCellType(EmptyCell.class, 1)); // TODO: change to FoodCell
        editButtons.get(2).addActionListener(e -> setCellType(EmptyCell.class, 2)); // TODO: change to KillerCell
    }

    public void setCellType(Class<? extends Cell> cellType, int index) {
        if (this.cellType == cellType) {
            this.cellType = null;
            editButtons.get(index).getModel().setPressed(false);
        } else {
            this.cellType = cellType;
            editButtons.get(index).getModel().setPressed(true);

            for (int i = 0; i < editButtons.size(); i++) {
                if (i != index) {
                    editButtons.get(i).getModel().setPressed(false);
                }
            }
        }
    }

    public void clickCell(Cell cell) {
        if (cellType != null && cell.getClass() != cellType) {
            try {
                cell = cellType.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
            }
        }
    }

}
