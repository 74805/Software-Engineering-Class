package Exe3;

import javax.swing.JFrame;

public class Board {
    private Cell[][] cells;
    private int size = 100;

    public Board() {
        cells = new Cell[size][size];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                // row[j] = new EmptyCell(i, j);
            }
        }
    }

    public void addCell(Cell cell, int x, int y) {
        cells[x][y] = cell;
    }

    public void display(JFrame frame) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].display(frame);
            }
        }
    }

    public void update() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].operate();
            }
        }
    }

}
