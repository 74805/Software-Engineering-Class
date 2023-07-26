package Exe3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    private int length;
    private int width;

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

        length = 0;
        width = 0;
        damage = 0;

        Random random = new Random();
        direction = random.nextInt(4);
        age = 0;
        energy = 0;
    }

    public Organism(Organism other, int distanceDif, int direction) throws NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // check if there is room to reproduce
        Cell[][] boardCells = other.board.getCells();
        if (direction == 0) {
            for (OrganismCell cell : other.cells) {
                if (cell.getX() + distanceDif < 0 || cell.getX() + distanceDif >= boardCells.length) {
                    return;
                }
                if (!(boardCells[cell.getX() + distanceDif][cell.getY()] instanceof EmptyCell)
                        || boardCells[cell.getX() + distanceDif][cell.getY()].getNextState() != State.SAME) {
                    return;
                }
            }
        } else {
            for (OrganismCell cell : other.cells) {
                if (cell.getY() + distanceDif < 0 || cell.getY() + distanceDif >= boardCells[0].length) {
                    return;
                }
                if (!(boardCells[cell.getX()][cell.getY() + distanceDif] instanceof EmptyCell)
                        || boardCells[cell.getX()][cell.getY() + distanceDif].getNextState() != State.SAME) {
                    return;
                }
            }
        }

        board = other.board;
        cells = new ArrayList<OrganismCell>();
        for (OrganismCell cell : other.cells) {
            Class<?> associatedClass = cell.getClass();
            Constructor<?> Copyconstructor = associatedClass.getDeclaredConstructor(Cell.class);

            OrganismCell newCell;
            if (direction == 0) {
                newCell = (OrganismCell) Copyconstructor
                        .newInstance(board.getCells()[cell.getX() + distanceDif][cell.getY()]);
            } else {
                newCell = (OrganismCell) Copyconstructor
                        .newInstance(board.getCells()[cell.getX()][cell.getY() + distanceDif]);
            }

            newCell.setOrganism(this);
            newCell.setNextOrganism(this);
            if (direction == 0) {
                newCell.setX(cell.getX() + distanceDif);
                newCell.setY(cell.getY());
            } else {
                newCell.setX(cell.getX());
                newCell.setY(cell.getY() + distanceDif);
            }
            addCell(newCell);
        }

        length = other.length;
        width = other.width;
        damage = 0;

        Random random = new Random();
        this.direction = random.nextInt(4);
        age = 0;
        energy = 0;
        other.energy = 0;
    }

    public List<OrganismCell> getCells() {
        return cells;
    }

    public void addCell(OrganismCell cell) {
        boolean minX = true;
        boolean maxX = true;
        boolean minY = true;
        boolean maxY = true;

        for (OrganismCell organismCell : cells) {
            if (organismCell.getX() <= cell.getX()) {
                minX = false;
            }

            if (organismCell.getX() >= cell.getX()) {
                maxX = false;
            }

            if (organismCell.getY() <= cell.getY()) {
                minY = false;
            }

            if (organismCell.getY() >= cell.getY()) {
                maxY = false;
            }
        }

        if (minX || maxX) {
            length++;
        }
        if (minY || maxY) {
            width++;
        }

        cells.add(cell);
        cell.setOrganism(this);
    }

    public void removeCell(OrganismCell cell) {
        if (!cells.contains(cell)) {
            return;
        }

        cells.remove(cell);

        boolean minX = true;
        boolean maxX = true;
        boolean minY = true;
        boolean maxY = true;

        for (OrganismCell organismCell : cells) {
            if (organismCell.getX() <= cell.getX()) {
                minX = false;
            }

            if (organismCell.getX() >= cell.getX()) {
                maxX = false;
            }

            if (organismCell.getY() <= cell.getY()) {
                minY = false;
            }

            if (organismCell.getY() >= cell.getY()) {
                maxY = false;
            }
        }

        if (minX || maxX) {
            length--;
        }
        if (minY || maxY) {
            width--;
        }
    }

    public void merge(Organism other) {
        for (OrganismCell cell : other.getCells()) {
            addCell(cell);
        }
    }

    public void operate() {
        try {
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

            age++;
            if (age >= cells.size() * LIFESPAN_MULTIPLIER) {
                die();
            }
        } catch (Exception e) {

        }

    }

    // when touched by a killer cell, an organism will take damage. Once it has
    // taken as much damage as it has cells in its body, it will die
    public void takeDamage() {
        damage += 1;

        if (damage >= 8 * cells.size()) {
            die();
        }
    }

    // when a mouth cell from the organism touches a food cell it will gain energy
    // if the organism has enough energy - it reproduces
    public void addEnergy() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        energy += 1;
        if (energy >= cells.size()) {
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
            if (nextCellType == null && nextCell.getState().getCellType() == EmptyCell.class) {
                continue;
            }

            if (nextCell instanceof OrganismCell && ((OrganismCell) nextCell).getNextOrganism() == this) {
                continue;
            }

            return;
        }

        for (OrganismCell cell : cells) {
            board.moveCell((OrganismCell) cell, cell.getX() + dx, cell.getY() + dy);
        }
    }

    public void reproduce() throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Random random = new Random();
        direction = random.nextInt(4);

        Organism reproduced = null;
        switch (direction) {
            case 0:
                reproduced = new Organism(this, -length - 1, 0);

                if (reproduced.getCells() != null) {
                    break;
                }
            case 1:
                reproduced = new Organism(this, width + 1, 1);

                if (reproduced.getCells() != null) {
                    break;
                }
            case 2:
                reproduced = new Organism(this, length + 1, 0);

                if (reproduced.getCells() != null) {
                    break;
                }
            case 3:
                reproduced = new Organism(this, -width - 1, 1);
                break;
        }

        if (reproduced.getCells() != null) {
            for (OrganismCell cell : reproduced.getCells()) {
                cell.setNextState(State.fromClass(cell.getClass()));
                cell.setNextOrganism(reproduced);
                board.changeCell(cell);
            }
        }

    }
}
