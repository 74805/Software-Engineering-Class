package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.State;
import Exe3.Cells.Cell;

public class KillerCell extends OrganismCell {

    public KillerCell() {
        super();

        state = State.KILLER;

        // create a new pink button
        button.setBackground(Color.PINK);
    }

    public KillerCell(Cell other) {
        super(other);

        state = State.KILLER;

        // create a new pink button
        button.setBackground(Color.PINK);
    }

    public KillerCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        state = State.KILLER;

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
