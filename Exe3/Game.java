package Exe3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Exe3.Cells.Cell;
import Exe3.Cells.EmptyCell;
import Exe3.Cells.FoodCell;
import Exe3.Cells.OrganismCells.ProducerCell;
import Exe3.Cells.OrganismCells.KillerCell;
import Exe3.Cells.OrganismCells.MouthCell;
import Exe3.Cells.OrganismCells.OrganismCell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private JFrame frame;
    private Board board;
    private JPanel boardPanel;

    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    private List<JButton> editButtons;
    private Class<? extends Cell> cellType;

    private Thread guiThread;

    private final int rows = 30;
    private final int cols = 40;

    public Game() {
        board = new Board(rows, cols, this::clickCell);
    }

    // start/resume the game
    private void play() {
        // disable the board buttons
        board.disable();

        stopButton.setEnabled(true);
        resetButton.setEnabled(false);
        startButton.setEnabled(false);

        // disable the edit buttons
        for (JButton button : editButtons) {
            button.setEnabled(false);
        }

        // clear the cellType
        cellType = null;
        for (JButton button : editButtons) {
            button.getModel().setPressed(false);
        }

        guiThread = new Thread(() -> {
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

    // pause the game
    private void stop() {
        // stop the thread
        guiThread.interrupt();

        // wait for the thread to die
        try {
            guiThread.join();
        } catch (InterruptedException e) {
        }

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
    }

    // reset the game
    private void reset() {
        // enable the board buttons
        board.reset(boardPanel, this::clickCell);

        // enable the edit buttons
        for (JButton button : editButtons) {
            button.setEnabled(true);
        }

        // enable the start button
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        resetButton.setEnabled(false);

        // repaint the boardPanel to update the changes
        boardPanel.revalidate();
        boardPanel.repaint();

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

    private void setEditButtons(JPanel panel) {
        editButtons = new ArrayList<>();
        editButtons.add(new JButton("Empty"));
        editButtons.add(new JButton("Food"));
        editButtons.add(new JButton("Killer"));
        editButtons.add(new JButton("Mouth"));
        editButtons.add(new JButton("Producer"));

        editButtons.get(0).setBackground(Color.GRAY);
        editButtons.get(1).setBackground(Color.GREEN);
        editButtons.get(2).setBackground(Color.PINK);
        editButtons.get(3).setBackground(Color.ORANGE);
        editButtons.get(4).setBackground(Color.CYAN);

        for (JButton button : editButtons) {
            // make the buttons square
            button.setPreferredSize(new Dimension(60, 60));

            // make the text smaller
            button.setFont(button.getFont().deriveFont(8f));

            panel.add(button);
        }

        // add actions to the buttons
        editButtons.get(0).addActionListener(e -> setCellType(EmptyCell.class, 0));
        editButtons.get(1).addActionListener(e -> setCellType(FoodCell.class, 1));
        editButtons.get(2).addActionListener(e -> setCellType(KillerCell.class, 2));
        editButtons.get(3).addActionListener(e -> setCellType(MouthCell.class, 3));
        editButtons.get(4).addActionListener(e -> setCellType(ProducerCell.class, 4));
    }

    private void setCellType(Class<? extends Cell> cellType, int index) {
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

    private void clickCell(Cell cell) {
        if (cellType != null && cell.getClass() != cellType) {
            try {
                // enable reset button
                resetButton.setEnabled(true);

                int index = boardPanel.getComponentZOrder(cell.getButton());
                int x = cell.getX();
                int y = cell.getY();

                boardPanel.remove(cell.getButton());

                cell = cellType.newInstance();
                cell.setClickHandler(this::clickCell);
                cell.setPosition(x, y);
                board.changeCell(cell);
                boardPanel.add(cell.getButton(), index);

                // repaint the boardPanel to update the changes
                boardPanel.revalidate();
                boardPanel.repaint();

                // add the cell to an organism if its an organism cell
                if (cell instanceof OrganismCell) {
                    board.addToOrganism((OrganismCell) cell);
                }

            } catch (InstantiationException | IllegalAccessException e) {
            }
        }
    }

}
