package Exe3.Cells.OrganismCells;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

import Exe3.State;
import Exe3.Cells.Cell;
import Exe3.Cells.FoodCell;

public class MouthCell extends OrganismCell {

    public MouthCell() {
        super();

        state = State.MOUTH;

        // create a new orange button
        button.setBackground(Color.ORANGE);
    }

    public MouthCell(Cell other) {
        super(other);

        state = State.MOUTH;

        // create a new orange button
        button.setBackground(Color.ORANGE);
    }

    public MouthCell(int x, int y, Consumer<Cell> clickHandler) {
        super(x, y, clickHandler);

        state = State.MOUTH;

        // create a new orange button
        button.setBackground(Color.ORANGE);
    }

    // eat an adjacent food cell (if exists)
    @Override
    public void operate(Cell[][] adjacentCells) throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (Cell[] row : adjacentCells) {
            for (Cell cell : row) {
                if (cell instanceof FoodCell && cell.getNextState() == State.SAME) {
                    cell.setNextState(State.EMPTY);
                    organism.addEnergy();
                    return;
                }
            }
        }

    }

}
