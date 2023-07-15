package Exe3;

import java.util.ArrayList;
import java.util.List;

import Exe3.Cells.Cell;
import Exe3.Cells.OrganismCells.OrganismCell;

public class Organism {
    private List<OrganismCell> cells;
    private int damage;
    private int energy; // how much food the organism ate


    // lifespan is calculated by multiplying the number of cells by the
    // hyperparameter Lifespan Multiplier
    private static final int lifespanMultiplier = 100;
    
    // age is the amout of ticks that have passed since the organism was created
    private int age;

    public Organism() {
        this.cells = new ArrayList<OrganismCell>();
        this.damage = 0;
        this.age = 0;
        this.energy = 0;
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


    // when a mouth cell from the organism touches food cell it will gain energy
    // if the organism has enough energy- it produces
    public void addEnergy() {
        energy += 1;
        if (energy >= cells.size()) { //produce if the ammount of energy is greater/equ than the number of cells
            energy -= cells.size();
            produce();
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

    public void produce(){
        //TODO
    }
}
