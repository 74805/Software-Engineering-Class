package Exe3.Cells.OrganismCells;

import java.util.function.Consumer;

import Exe3.Cells.Cell;

public abstract class OrganismCell extends Cell {
    public OrganismCell() {
        super();
    }

    public OrganismCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);
    }

}
