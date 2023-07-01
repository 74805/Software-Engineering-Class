package Exe3.Cells.OrganismCells;

import Exe3.Organism;

public interface OrganismCell {
    public void operate();

    public void setOrganism(Organism organism);

    public default void die() {
        // TODO: turn into food cell
    }
}
