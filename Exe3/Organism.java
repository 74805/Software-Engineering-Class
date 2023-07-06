package Exe3;

import java.util.ArrayList;
import java.util.List;

import Exe3.Cells.OrganismCells.OrganismCell;

public class Organism {
    private List<OrganismCell> cells;
    private List<Organism> neighbors;
    private int damage;

    // lifespan is calculated by multiplying the number of cells by the
    // hyperparameter Lifespan Multiplier
    private static final int lifespanMultiplier = 100;

    // age is the amout of ticks that have passed since the organism was created
    private int age;

    public Organism(OrganismCell cell) {
        this.cells = new ArrayList<OrganismCell>();
        this.cells.add(cell);
        this.neighbors = new ArrayList<Organism>();
        this.damage = 0;
        this.age = 0;
    }

    public void addCell(OrganismCell cell) {
        cells.add(cell);
    }

    // When touched by a killer cell, an organism will take damage. Once it has
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
}
