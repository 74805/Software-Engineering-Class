package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cells.Cell;

public class ProducerCell extends OrganismCell {

    public ProducerCell() {
        super();

        // create a new gray buttone
        button.setBackground(Color.BLUE);
    }

    public ProducerCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new gray buttone
        button.setBackground(Color.BLUE);
    }

    // damage all adjacent organisms
    @Override
    public void operate(Cell[][] adjacentCells) {
        //TODO

    }

}
