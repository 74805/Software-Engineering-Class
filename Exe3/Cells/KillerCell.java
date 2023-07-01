package Exe3.Cells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.Cell;

public class KillerCell extends Cell {

    public KillerCell() {
        super();

        // Create a new gray buttone
        button.setBackground(Color.PINK);
    }

    public KillerCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        // Create a new gray buttone
        button.setBackground(Color.PINK);
    }

    @Override
    public void operate() {
        // TODO
    }

}
