package Exe3;

import java.awt.Dimension;

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

    public abstract void operate();

    public abstract void display(JPanel panel);

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
