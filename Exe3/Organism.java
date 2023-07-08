package Exe3;

import java.util.ArrayList;
import java.util.List;

import Exe3.Cells.Cell;
import Exe3.Cells.OrganismCells.OrganismCell;

public class Organism {
    private Board board;
    private List<OrganismCell> cells;
    private int damage;

    // direction is the direction the organism is currently moving in
    // 0 = up, 1 = right, 2 = down, 3 = left
    protected int direction;

    // lifespan is calculated by multiplying the number of cells by the
    // hyperparameter Lifespan Multiplier
    private static final int lifespanMultiplier = 100;

    // age is the amout of ticks that have passed since the organism was created
    private int age;

    public Organism() {
        this.cells = new ArrayList<OrganismCell>();
        this.damage = 0;
        this.direction = (int) (Math.random() * 4);
        this.age = 0;
    }

    public List<OrganismCell> getCells() {
        return cells;
    }

    public void addCell(OrganismCell cell) {
        cells.add(cell);
    }

    public void merge(Organism other) {
        for (OrganismCell cell : other.getCells()) {
            cells.add(cell);
        }
    }

    // when touched by a killer cell, an organism will take damage. Once it has
    // taken as much damage as it has cells in its body, it will die
    public void takeDamage() {
        damage += 1;

        if (damage >= cells.size()) {
            die();
        }
    }

    public void increaseAge() {
        age += 1;

        if (age >= cells.size() * lifespanMultiplier) {
            die();
        }
    }

    private void die() {
        // turn all cells into food cells
        for (OrganismCell cell : cells) {
            cell.die();
        }
    }

    public boolean isAdjacent(Cell cell) {
        for (OrganismCell organismCell : cells) {
            if (organismCell.isAdjacent(cell)) {
                return true;
            }
        }

        return false;
    }

    public void move() {
        int dx = 0, dy = 0;
        if (Math.random() < 0.2) {
            direction = (int) Math.random() * 4;

            switch (direction) {
                case 0:
                    dy = -1;
                    break;
                case 1:
                    dx = 1;
                    break;
                case 2:
                    dy = 1;
                    break;
                case 3:
                    dx = -1;
                    break;
            }
        }

        for (OrganismCell cell : cells) {
            board.moveCell((OrganismCell) cell, cell.getX() + dx, cell.getY() + dy);
        }
    }

    public void rotateRight() {
        // TODO
    }

    public void rotateLeft() {
        // TODO
    }
}
