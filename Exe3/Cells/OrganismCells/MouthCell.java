package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cells.Cell;
import Exe3.Cells.FoodCell;

public class MouthCell extends OrganismCell {

    public MouthCell() {
        super();

        // create a new orange buttone
        button.setBackground(Color.ORANGE);
    }

    public MouthCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new orange buttone
        button.setBackground(Color.ORANGE);
    }

    // damage all adjacent organisms
    @Override
    public void operate(Cell[][] adjacentCells) {
        for (Cell[] row : adjacentCells) {
            for (Cell cell : row) {
                if (cell != this && cell instanceof FoodCell) {
                    ((FoodCell) cell).disable();
                    organism.addEnergy();
                }
            }
        }

    }

}
