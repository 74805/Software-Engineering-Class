package Exe3;

import java.util.ArrayList;
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

    public void addCell(Cell cell, int x, int y) {
        cells[x][y] = cell;
    }

    public void replaceCell(Cell cell, int x, int y) {
        cells[x][y] = cell;
    }

    public void display(JPanel panel) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].display(panel);
            }
        }
    }

    public void update() {
        // TODO
        int a = 1;
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

    // If the cell is adjacent to an organism, add it to the organism, Otherwise,
    // create a new organism
    public void addToOrganism(OrganismCell cell) {
        for (Organism organism : organisms) {
            if (organism.isAdjacent(cell)) {
                organism.addCell((OrganismCell) cell);
                return;
            }
        }

        Organism organism = new Organism();
        organism.addCell((OrganismCell) cell);
        organisms.add(organism);
    }

}
