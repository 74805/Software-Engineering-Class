package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cells.Cell;

public class KillerCell extends OrganismCell {

    public KillerCell() {
        super();

        // create a new pink button
        button.setBackground(Color.PINK);
    }

    public KillerCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new pink button
        button.setBackground(Color.PINK);
    }

    public KillerCell(Cell some_cell) {
        super(some_cell.getX(), some_cell.getY(), some_cell.getClickHandler());

        // create a new pink button
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
