package Exe3;

import javax.swing.JPanel;

import Exe3.Cells.EmptyCell;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private Cell[][] cells;
    private final int size = 100;

    private Thread uiThread;
    private final ReentrantLock lock = new ReentrantLock();

    public Board() {
        cells = new Cell[size][size];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[j][i] = new EmptyCell(j * 15, i * 15);
            }
        }
    }

    public void addCell(Cell cell, int x, int y) {
        cells[x][y] = cell;
    }

    public void display(JPanel panel) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].display(panel);
            }
        }

        this.uiThread = new Thread(() -> {
            while (true) {
                // updateUI and update cannot run at the same time
                lock.lock();
                try {
                    updateUI();
                } finally {
                    lock.unlock();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        this.uiThread.start();
    }

    public void update() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].operate();
            }
        }
    }

    public void updateUI() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[j][i].updateUI();
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

}
