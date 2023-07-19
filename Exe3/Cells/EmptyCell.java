package Exe3.Cells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.State;

public class EmptyCell extends Cell {

    public EmptyCell() {
        super();

        state = State.EMPTY;

        // create a new gray button
        button.setBackground(Color.GRAY);
    }

    public EmptyCell(Cell other) {
        super(other);

        state = State.EMPTY;

        button.setBackground(Color.GRAY);
    }

    public EmptyCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new gray button
        button.setBackground(Color.GRAY);
    }

}
