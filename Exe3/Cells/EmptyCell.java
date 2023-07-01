package Exe3.Cells;

import java.awt.Color;
import javax.swing.JPanel;

import Exe3.Cell;

public class EmptyCell extends Cell {

    public EmptyCell() {
        super();

        // Create a new gray buttone
        button.setBackground(Color.GRAY);
    }

    @Override
    public void operate() {
        // Empty cell does nothing
    }

    @Override
    public void display(JPanel panel) {
        panel.add(button);
    }

}
