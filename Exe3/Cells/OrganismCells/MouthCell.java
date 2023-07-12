package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cells.Cell;

public class MouthCell extends OrganismCell {

    public MouthCell() {
        super();

        // create a new gray buttone
        button.setBackground(Color.PINK);
    }

    public MouthCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new gray buttone
        button.setBackground(Color.PINK);
    }

    // damage all adjacent organisms
    @Override
    public void operate(Cell[][] adjacentCells) {
        for (Cell[] row : adjacentCells) {
            for (Cell cell : row) {
                if (cell != this && cell instanceof OrganismCell && ((OrganismCell) cell).organism != organism) {
                    ((OrganismCell) cell).organism.takeDamage();
                }
            }
        }

    }

}
