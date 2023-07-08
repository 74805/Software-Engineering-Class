package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cells.Cell;

public class KillerCell extends OrganismCell {

    public KillerCell() {
        super();

        // create a new gray buttone
        button.setBackground(Color.PINK);
    }

    public KillerCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // create a new gray buttone
        button.setBackground(Color.PINK);
    }

    @Override
    public void operate() {
        // TODO
    }

}
