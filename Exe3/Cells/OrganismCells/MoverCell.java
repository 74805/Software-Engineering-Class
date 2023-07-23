package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.State;
import Exe3.Cells.Cell;

public class MoverCell extends OrganismCell {
    public MoverCell() {
        super();

        state = State.MOVER;

        // create a new orange button
        button.setBackground(Color.YELLOW);
    }

    public MoverCell(Cell other) {
        super(other);

        state = State.MOVER;

        // create a new orange button
        button.setBackground(Color.YELLOW);
    }

    public MoverCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        state = State.MOVER;

        // create a new orange button
        button.setBackground(Color.YELLOW);
    }

    // move and rotate the organism randomly
    @Override
    public void operate(Cell[][] adjacentCells) {
        // move the organism randomly
        if (Math.random() < 0.5) {
            return;
        }

        organism.move();

        // rotate the organism randomly
        // if (Math.random() < 0.1) {
        // switch ((int) (Math.random() * 3)) {
        // case 0:
        // organism.rotateRight();
        // break;
        // case 1:
        // organism.rotateLeft();
        // break;
        // case 2:
        // organism.rotateRight();
        // organism.rotateRight();
        // break;
        // }

        // // TODO: check if the new rotation is valid
        // }

    }

}
