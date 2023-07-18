package Exe3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

import Exe3.Cells.Cell;
import Exe3.Cells.EmptyCell;
import Exe3.Cells.OrganismCells.OrganismCell;

public class Board {
    private Cell[][] cells;
    private List<Organism> organisms;

    public Board(int rows, int cols, Consumer<Cell> clickHandler) {
        organisms = new ArrayList<Organism>();
        cells = new Cell[rows][cols];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new EmptyCell(i, j, clickHandler);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void changeCell(Cell cell) {
        cells[cell.getX()][cell.getY()] = cell;
    }

    public void display(JPanel panel) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].display(panel);
            }
        }
    }

    public void update() {
        Collections.shuffle(organisms);

        for (Organism organism : organisms) {
            organism.operate();
        }

        // go over all cells and replace with the next state
        State nextState;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                nextState = cell.getNextState();
                if (nextState != State.SAME) {
                    Class<?> associatedClass = nextState.getCellType();
                    java.lang.reflect.Constructor<?> copyConstructor = associatedClass
                            .getDeclaredConstructor(associatedClass);
                    Cell newCell = (Cell) copyConstructor.newInstance(cell);

                    changeCell(newCell);
                }
            }
        }
    }

    public void disable() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].disable();
            }
        }
    }

    public void enable() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].enable();
            }
        }
    }

    public void reset(JPanel panel, Consumer<Cell> clickHandler) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (!(cells[i][j] instanceof EmptyCell)) {
                    int index = panel.getComponentZOrder(cells[i][j].getButton());
                    panel.remove(cells[i][j].getButton());

                    cells[i][j] = new EmptyCell(i, j, clickHandler);
                    panel.add(cells[i][j].getButton(), index);
                }
            }
        }
    }

    // if the cell is adjacent to an organism, add it to the organism, Otherwise,
    // create a new organism
    public void addToOrganism(OrganismCell cell) {
        Organism adjacentOrganism = null;
        for (int i = 0; i < organisms.size(); i++) {
            Organism organism = organisms.get(i);
            if (organism.isAdjacent(cell)) {
                // if the cell is adjacent to more than one organism, merge them
                if (adjacentOrganism == null) {
                    organism.addCell((OrganismCell) cell);
                    adjacentOrganism = organism;
                } else {
                    adjacentOrganism.merge(organism);
                    organisms.remove(organism);
                    i--;
                }
            }
        }

        // if the cell is adjacent to an organism, add it to the organism
        if (adjacentOrganism != null) {
            return;
        }

        Organism organism = new Organism();
        organism.addCell((OrganismCell) cell);
        organisms.add(organism);
    }

}
