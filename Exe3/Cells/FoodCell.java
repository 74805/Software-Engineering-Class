package Exe3.Cells; //in Cells, not Organism Cell
import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cells.Cell;

public class FoodCell extends Cell {

    public FoodCell() { // inherits from its parent and set color to GREEN
        super();
        // create a new gray buttone
        button.setBackground(Color.GREEN);
    }

    public FoodCell(int x, int y, Consumer<Cell> clickHandler) { //inherits from its parent and set color to GREEN
        super(x, y, clickHandler);
        // create a new gray buttone
        button.setBackground(Color.GREEN);
    }

}
