package Exe3.Cells;

import java.awt.Color;
import java.util.function.Consumer;

public class EmptyCell extends Cell {

    public EmptyCell() {
        super();

        // Create a new gray buttone
        button.setBackground(Color.GRAY);
    }

    public EmptyCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // Create a new gray buttone
        button.setBackground(Color.GRAY);
    }

    @Override
    public void operate() {
        // Empty cell does nothing
    }

}
