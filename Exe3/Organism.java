package Exe3;

import java.util.ArrayList;
import java.util.List;

import Exe3.Cells.Cell;
import Exe3.Cells.OrganismCells.OrganismCell;

public class Organism {
    private List<OrganismCell> cells;

    private int maxX = 100;
    private int minX = -1;
    private int maxY = 100;
    private int minY = -1;

    private int damage;
    private int energy; // how much food the organism ate

    // lifespan is calculated by multiplying the number of cells by the
    // hyperparameter Lifespan Multiplier
    public static final int LIFESPAN_MULTIPLIER = 100;

    // age is the amout of ticks that have passed since the organism was created
    private int age;

    public Organism() {
        cells = new ArrayList<OrganismCell>();

        damage = 0;
        age = 0;
        energy = 0;
    }

    public Organism(Organism other) {
        // TODO: create copy constructor
        // (for reproduction)
    }

    public List<OrganismCell> getCells() {
        return cells;
    }

    public void addCell(OrganismCell cell) {
        cells.add(cell);
        cell.setOrganism(this);

        if (cell.getX() > maxX)
            maxX = cell.getX();
        else if (cell.getX() < minX)
            minX = cell.getX();
        if (cell.getY() > maxY)
            maxY = cell.getY();
        else if (cell.getY() < minY)
            minY = cell.getY();
    }

    public void removeCell(OrganismCell cell) {
        cells.remove(cell);
    }

    public void merge(Organism other) {
        for (OrganismCell cell : other.getCells()) {
            cells.add(cell);
        }
    }

    public void operate(Cell[][] boardCells) {
        boolean moved = false;
        for (OrganismCell cell : cells) {
            if (/* cell instanceof MoverCell */ false) {
                if (!moved) {
                    moved = true;
                    cell.operate(cell.getAdjacentCells(boardCells));
                }
            } else {
                cell.operate(cell.getAdjacentCells(boardCells));
            }
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

    // when a mouth cell from the organism touches a food cell it will gain energy
    // if the organism has enough energy - it reproduces
    public void addEnergy() {
        energy += 1;
        if (energy >= cells.size()) {
            energy = 0;
            reproduce();
        }
    }

    public void increaseAge() {
        age += 1;

        if (age >= cells.size() * LIFESPAN_MULTIPLIER) {
            die();
        }
    }

    // turn all cells into food cells
    private void die() {
        for (OrganismCell cell : cells) {
            cell.setNextState(State.FOOD);
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

    public int getmaxX() {
        return maxX;
    }

    public int getmaxY() {
        return maxY;
    }

    public int getminX() {
        return minX;
    }

    public int getminY() {
        return minY;
    }

    public void reproduce() {
        // TODO
        // after creating mover cell
    }
}
