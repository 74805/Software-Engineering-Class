package Exe3.Cells;

import java.awt.Color;
import java.util.function.Consumer;

public class FoodCell extends Cell { //same as empty cell, but green

    public FoodCell() {
        super();

        // create a new green buttone
        button.setBackground(Color.GREEN);
    }

    public FoodCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new green buttone
        button.setBackground(Color.GREEN);
    }
}
