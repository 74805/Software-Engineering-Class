package Exe3.Cells;

import java.awt.Color;
import java.util.function.Consumer;

public class EmptyCell extends Cell {

    public EmptyCell() {
        super();

        // create a new gray button
        button.setBackground(Color.GRAY);
    }

    public EmptyCell(EmptyCell other) {
        super(other);

        button.setBackground(Color.GRAY);
    }

    public EmptyCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new gray button
        button.setBackground(Color.GRAY);
    }

}
