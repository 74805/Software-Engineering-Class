package Exe3.Cells;

import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Cell {
    private boolean alive;
    private int x;
    private int y;

    protected JButton button;

    public Cell() {
        this.alive = true;

        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
    }

    public Cell(int x, int y, Consumer<Cell> clickHandler) {
        this.alive = true;
        this.x = x;
        this.y = y;

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

    public abstract void operate();

    public void display(JPanel panel) {
        panel.add(button);
    }

    public void disable() {
        button.setEnabled(false);
    }

    public void enable() {
        button.setEnabled(true);
    }

    public boolean isAlive() {
        return alive;
    }
}
