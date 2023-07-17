package Exe3.Cells;

import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Cell {
    private int x;
    private int y;

    // private State next_state;
    protected JButton button;
    protected Consumer<Cell> clickHandler;

    public Cell() {
        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
    }

    public Cell(int x, int y, Consumer<Cell> clickHandler) {
        this.x = x;
        this.y = y;
        this.clickHandler = clickHandler;

        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.addActionListener(e -> {
            clickHandler.accept(this);
        });
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setClickHandler(Consumer<Cell> clickHandler) {
        button.addActionListener(e -> {
            clickHandler.accept(this);
        });
    }

    public JButton getButton() {
        return button;
    }

    public Consumer<Cell> getClickHandler() {
        return clickHandler;
    }

    public void display(JPanel panel) {
        panel.add(button);
    }

    public void disable() {
        button.setEnabled(false);
    }

    public void enable() {
        button.setEnabled(true);
    }

    public boolean isAdjacent(Cell cell) {
        return Math.abs(cell.x - x) <= 1 || Math.abs(cell.y - y) <= 1;
    }
}
