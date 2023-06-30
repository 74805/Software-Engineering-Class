package Exe3;

import javax.swing.JFrame;

public abstract class Cell {
    private int x;
    private int y;
    private boolean alive;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    public abstract void operate();

    public abstract void display(JFrame frame);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return alive;
    }
}
