package Exe3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Exe3.Cells.Cell;
import Exe3.Cells.EmptyCell;
import Exe3.Cells.OrganismCells.MoverCell;
import Exe3.Cells.OrganismCells.OrganismCell;

public class Organism {
    private Board board;
    private List<OrganismCell> cells;

    private int maxX = 100;
    private int minX = -1;
    private int maxY = 100;
    private int minY = -1;

    private int damage;
    private int energy; // how much food the organism ate

    // direction is the direction the organism is currently moving in
    // 0 = up, 1 = right, 2 = down, 3 = left
    protected int direction;

    // lifespan is calculated by multiplying the number of cells by the
    // hyperparameter Lifespan Multiplier
    public static final int LIFESPAN_MULTIPLIER = 100;

    // age is the amout of ticks that have passed since the organism was created
    private int age;

    public Organism(Board board) {
        this.board = board;
        cells = new ArrayList<OrganismCell>();

        damage = 0;
        direction = (int) (Math.random() * 4);
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

    public void operate() {
        boolean moved = false;
        for (OrganismCell cell : cells) {
            if (cell instanceof MoverCell) {
                if (!moved) {
                    moved = true;
                    cell.operate(cell.getAdjacentCells(board.getCells()));
                }
            } else {
                cell.operate(cell.getAdjacentCells(board.getCells()));
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

    public void move() {
        int dx = 0, dy = 0;
        Random random = new Random();

        // change direction with 25% probability
        if (random.nextInt(4) == 0) {
            direction = random.nextInt(4);
        }

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

        // check if moving in this direction is possible
        Cell[][] boardCells = board.getCells();
        for (OrganismCell cell : cells) {
            int newX = cell.getX() + dx;
            int newY = cell.getY() + dy;

            if (newX < 0 || newX >= boardCells.length || newY < 0 || newY >= boardCells[0].length) {
                return;
            }

            Cell nextCell = boardCells[newX][newY];
            Class<? extends Cell> nextCellType = nextCell.getNextState().getCellType();
            // if the next cell hasn't changed yet and is empty, continue
            if (nextCellType == null && nextCell instanceof EmptyCell) {
                continue;
            } else {
                if (nextCellType != null) {
                    return;
                }

                if (!(nextCell instanceof OrganismCell) || ((OrganismCell) nextCell).getNextOrganism() != this) {
                    return;
                }
            }
        }

        for (OrganismCell cell : cells) {
            board.moveCell((OrganismCell) cell, cell.getX() + dx, cell.getY() + dy);
        }
    }

    // public void rotateRight() {
    // // TODO
    // }

    // public void rotateLeft() {
    // // TODO
    // }

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
