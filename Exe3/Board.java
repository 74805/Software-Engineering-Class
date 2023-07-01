package Exe3;

import java.util.function.Consumer;

import javax.swing.JPanel;

import Exe3.Cells.Cell;
import Exe3.Cells.EmptyCell;

public class Board {
    private Cell[][] cells;

    public Board(int rows, int cols, Consumer<Cell> clickHandler) {
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
                    int index = panel.getComponentZOrder(cells[i][j].button);
                    panel.remove(cells[i][j].getButton());

                    cells[i][j] = new EmptyCell(i, j, clickHandler);
                    panel.add(cells[i][j].getButton(), index);
                }
            }
        }
    }

}
