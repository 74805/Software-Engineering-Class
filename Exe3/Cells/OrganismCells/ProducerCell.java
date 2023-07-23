package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.util.function.Consumer;

import Exe3.State;
import Exe3.Cells.Cell;
import Exe3.Cells.EmptyCell;

public class ProducerCell extends OrganismCell {

    public ProducerCell() {
        super();

        state = State.PRODUCER;

        // create a new cyan button
        button.setBackground(Color.CYAN);
    }

    public ProducerCell(Cell other) {
        super(other);

        state = State.PRODUCER;

        button.setBackground(Color.CYAN);
    }

    public ProducerCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        state = State.PRODUCER;

        // create a new cyan button
        button.setBackground(Color.CYAN);
    }

    // produces a new food cell in a random adjacent cell
    @Override
    public void operate(Cell[][] adjacentCells) {
        // produce a new food cell with a 5% chance
        if (Math.random() > 0.05) {
            return;
        }
        int index = (int) (Math.random() * 9);
        while (index == 4) {
            index = (int) (Math.random() * 9);
        }

        Cell cell = adjacentCells[index / 3][index % 3];
        if (cell instanceof EmptyCell && cell.getNextState() == State.SAME) {
            cell.setNextState(State.FOOD);
        }
    }

}
