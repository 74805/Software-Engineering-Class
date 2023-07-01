package Exe3;

import java.util.List;

import Exe3.Cells.OrganismCells.OrganismCell;

public class Organism {
    private List<OrganismCell> cells;
    private int damage;

    // lifespan is calculated by multiplying the number of cells by the
    // hyperparameter Lifespan Multiplier
    private static final int lifespanMultiplier = 100;

    public Organism(List<OrganismCell> cells) {
        this.cells = cells;
        this.damage = 0;
    }

    public void takeDamage() {
        damage += 1;

        if (damage >= cells.size() * lifespanMultiplier) {
            die();
        }
    }

    private void die() {
        // turn all cells into food cells

    }

}
