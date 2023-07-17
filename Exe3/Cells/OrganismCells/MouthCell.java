package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cells.Cell;
import Exe3.Cells.FoodCell;

public class MouthCell extends OrganismCell {

    public MouthCell() {
        super();

        // create a new orange button
        button.setBackground(Color.ORANGE);
    }

    public MouthCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new orange button
        button.setBackground(Color.ORANGE);
    }

    public MouthCell(Cell some_cell) {
        super(some_cell.getX(), some_cell.getY(), some_cell.getClickHandler());

        // create a new orange button
        button.setBackground(Color.ORANGE);
    }

    // damage all adjacent organisms
    @Override
    public void operate(Cell[][] adjacentCells) {
        for (Cell[] row : adjacentCells) {
            for (Cell cell : row) {
                if (cell instanceof FoodCell /* && nextState == SAME */) {
                    // TODO: Turn it to an empty cell
                    organism.addEnergy();
                    return;
                }
            }
        }

    }

}
