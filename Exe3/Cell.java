package Exe3;

import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Cell {
    private boolean alive;

    protected JButton button;

    public Cell() {
        this.alive = true;

        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
    }

    public Cell(Consumer<Cell> clickHandler) {
        this.alive = true;

        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.addActionListener(e -> {
            clickHandler.accept(this);
        });
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
