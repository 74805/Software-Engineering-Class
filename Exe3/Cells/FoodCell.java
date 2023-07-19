package Exe3.Cells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.State;

public class FoodCell extends Cell {

    public FoodCell() {
        super();

        state = State.FOOD;

        // create a new green button
        button.setBackground(Color.GREEN);
    }

    public FoodCell(Cell other) {
        super(other);

        button.setBackground(Color.GREEN);
    }

    public FoodCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new green button
        button.setBackground(Color.GREEN);
    }

}
