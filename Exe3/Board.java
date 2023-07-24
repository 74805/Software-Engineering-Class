package Exe3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        Cell oldCell = cells[cell.getX()][cell.getY()];
        if (oldCell instanceof OrganismCell) {
            OrganismCell organismCell = (OrganismCell) oldCell;
            Organism organism = organismCell.getOrganism();
            organism.removeCell(organismCell);
        }

        cells[cell.getX()][cell.getY()] = cell;
    }

    public void display(JPanel panel) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].display(panel);
            }
        }
    }

    public void update() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Collections.shuffle(organisms);

        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).getCells().size() == 0) {
                organisms.remove(i);
                i--;
            } else {
                organisms.get(i).operate();
            }
        }

        // go over all cells and replace with the next state
        State nextState;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                nextState = cell.getNextState();
                if (nextState != State.SAME) {
                    // call the copy constructor
                    Class<?> associatedClass = nextState.getCellType();
                    Constructor<?> copyConstructor = associatedClass.getDeclaredConstructor(Cell.class);
                    Cell newCell = (Cell) copyConstructor.newInstance(cell);

                    if (cell instanceof OrganismCell && cell.getNextOrganism() == null) {
                        ((OrganismCell) cell).getOrganism().removeCell((OrganismCell) cell);
                    }

                    if (newCell instanceof OrganismCell) {
                        Organism nextOrganism = cell.getNextOrganism();
                        if (nextOrganism != null) {
                            nextOrganism.addCell((OrganismCell) newCell);

                            if (!organisms.contains(nextOrganism)) {
                                organisms.add(nextOrganism);
                            }
                        }
                    }

                    // finally, replace the cell
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
        Cell cell;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (!(cells[i][j] instanceof EmptyCell)) {
                    cell = new EmptyCell(cells[i][j]);
                    cell.setClickHandler(clickHandler);
                    changeCell(cell);
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

        // if the cell is not adjacent to an organism, create a new one
        if (adjacentOrganism == null) {
            Organism organism = new Organism(this);
            organism.addCell(cell);
            organisms.add(organism);
        }
    }

    public void moveCell(OrganismCell cell, int x, int y) {
        cells[x][y].setNextState(cell.getState());
        cells[x][y].setNextOrganism(cell.getOrganism());
        if (cell.getNextState() == State.SAME) {
            cell.setNextState(State.EMPTY);
            cell.setNextOrganism(null);
        }
    }

}
