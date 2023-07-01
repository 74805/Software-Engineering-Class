package Exe3.Cells.OrganismCells;

import java.util.function.Consumer;

import Exe3.Organism;
import Exe3.Cells.Cell;

public abstract class OrganismCell extends Cell {
    private Organism organism;

    public OrganismCell() {
        super();
    }

    public OrganismCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);
    }

    public abstract void operate();

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public void die() {
        // TODO: turn into food cell
    }
}
