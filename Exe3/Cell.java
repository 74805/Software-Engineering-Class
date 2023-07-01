package Exe3;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Cell {
    private boolean alive;
    protected JButton button;

    public Cell() {
        this.alive = true;
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

    public void updateUI() {
        button.updateUI();
    }
}
