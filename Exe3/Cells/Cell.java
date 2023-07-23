package Exe3.Cells;

import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

import Exe3.Organism;
import Exe3.State;

public abstract class Cell {
    protected int x;
    protected int y;

    protected State state;
    protected State nextState;
    protected Organism nextOrganism;

    protected JButton button;

    public Cell() {
        nextState = State.SAME;

        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
    }

    public Cell(Cell other) {
        nextState = State.SAME;

        x = other.x;
        y = other.y;

        button = other.button;
    }

    public Cell(int x, int y, Consumer<Cell> clickHandler) {
        nextState = State.SAME;

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

    public State getNextState() {
        return nextState;
    }

    public State getState() {
        return state;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setNextOrganism(Organism nextOrganism) {
        this.nextOrganism = nextOrganism;
    }

    public Organism getNextOrganism() {
        return nextOrganism;
    }

    public void setClickHandler(Consumer<Cell> clickHandler) {
        button.addActionListener(e -> {
            clickHandler.accept(this);
        });
    }

    public JButton getButton() {
        return button;
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
}
