package Exe3.Cells;

// import Exe3.State;
import java.awt.Color;
import java.util.function.Consumer;

public class FoodCell extends Cell {

    public FoodCell() {
        super();

        // create a new green button
        button.setBackground(Color.GREEN);
    }

    public FoodCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new green button
        button.setBackground(Color.GREEN);
    }

    public FoodCell(Cell some_cell) {
        super(some_cell.getX(), some_cell.getY(), some_cell.getClickHandler());
        // create a new green button
        button.setBackground(Color.GREEN);
    }

}
