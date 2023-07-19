package Exe3.Cells.OrganismCells;

import java.util.function.Consumer;

import Exe3.Organism;
import Exe3.Cells.Cell;

public abstract class OrganismCell extends Cell {
    protected Organism organism;

    public OrganismCell() {
        super();
    }

    public OrganismCell(Cell other) {
        super(other);

        if (other instanceof OrganismCell) {
            organism = ((OrganismCell) other).organism;
        }
    }

    public OrganismCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);
    }

    // adjacentCells is a 3x3 matrix of cells, where the middle cell is the current
    public abstract void operate(Cell[][] adjacentCells);

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public Cell[][] getAdjacentCells(Cell[][] boardCells) {
        Cell[][] adjacentCells = new Cell[3][3];

        for (int i = 0; i < adjacentCells.length; i++) {
            for (int j = 0; j < adjacentCells[i].length; j++) {
                int x = this.x + i - 1;
                int y = this.y + j - 1;

                if (x < 0 || x >= boardCells.length || y < 0 || y >= boardCells[x].length) {
                    adjacentCells[i][j] = null;
                } else {
                    adjacentCells[i][j] = boardCells[x][y];
                }
            }
        }

        return adjacentCells;
    }

    public boolean isAdjacent(Cell cell) {
        return Math.abs(cell.getX() - x) <= 1 && Math.abs(cell.getY() - y) <= 1;
    }
}
