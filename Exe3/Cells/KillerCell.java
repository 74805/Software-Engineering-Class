package Exe3.Cells;

import java.awt.Color;

import Exe3.Cell;

public class KillerCell extends Cell {

    public KillerCell() {
        super();

        // Create a new gray buttone
        button.setBackground(Color.PINK);
    }

    @Override
    public void operate() {
        // Empty cell does nothing
    }

}
