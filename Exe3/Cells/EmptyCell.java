package Exe3.Cells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cell;

public class EmptyCell extends Cell {

    public EmptyCell() {
        super();

        // Create a new gray buttone
        button.setBackground(Color.GRAY);
    }

    public EmptyCell(Consumer<Cell> clickHandler) {
        super(clickHandler);

        // Create a new gray buttone
        button.setBackground(Color.GRAY);
    }

    @Override
    public void operate() {
        // Empty cell does nothing
    }

}
