package Exe3.Cells;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import Exe3.Cell;

public class EmptyCell extends Cell {

    public EmptyCell(int x, int y) {
        super();
    }

    @Override
    public void operate() {
        // Empty cell does nothing
    }

    @Override
    public void display(JPanel panel) {
        // Create a new gray button
        button = new JButton();
        button.setPreferredSize(new Dimension(15, 15));
        button.setBackground(Color.GRAY);
        panel.add(button);

    }

}
