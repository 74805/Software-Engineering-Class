package Exe3.Cells;
import Exe3.State;
import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;


public abstract class Cell {
    //private boolean alive;
    private int x;
    private int y;
    private State next_state; //what kind of cell are we supposed to be
    protected JButton button;
    protected Consumer<Cell> clickHandler;
    public Cell() {
        this.next_state = State.SAME;
        button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
    }

    public Cell(int x, int y, Consumer<Cell> clickHandler) {
        this.next_state = State.SAME;
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

    public Consumer<Cell> getClickHandler(){
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

    //new: get and set next state
    public State getNextState() {
        return next_state;
    }

    public void setNextState(State s){
        this.next_state = s;
    }

    public boolean isAdjacent(Cell cell) {
        return Math.abs(cell.getX() - getX()) <= 1 && Math.abs(cell.getY() - getY()) <= 1;
    }
}
