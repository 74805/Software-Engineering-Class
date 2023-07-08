package Exe3.Cells.OrganismCells;

import java.util.function.Consumer;

import Exe3.Organism;
import Exe3.Cells.Cell;

public abstract class OrganismCell extends Cell {
    protected Organism organism;

    public OrganismCell() {
        super();
    }

    public OrganismCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);
    }

    // adjacentCells is a 3x3 matrix of cells, where the middle cell is the current
    public abstract void operate(Cell[][] adjacentCells);

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public void die() {
        // TODO: turn into food cell
    }

    public boolean isAdjacent(Cell cell) {
        return Math.abs(cell.getX() - getX()) <= 1 && Math.abs(cell.getY() - getY()) <= 1;
    }
}
