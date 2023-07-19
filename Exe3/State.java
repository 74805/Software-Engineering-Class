package Exe3;

import Exe3.Cells.Cell;
import Exe3.Cells.EmptyCell;
import Exe3.Cells.OrganismCells.KillerCell;
import Exe3.Cells.OrganismCells.MouthCell;
import Exe3.Cells.OrganismCells.ProducerCell;
import Exe3.Cells.FoodCell;
//TODO: import Exe3.Cells.OrganismCells.MoverCell; 

public enum State {
    SAME(null),
    EMPTY(EmptyCell.class),
    FOOD(FoodCell.class),
    KILLER(KillerCell.class),
    MOUTH(MouthCell.class),
    PRODUCER(ProducerCell.class);
    // TODO: MOVER(MoverCells.class);

    private Class<? extends Cell> associatedClass;

    State(Class<? extends Cell> associatedClass) {
        this.associatedClass = associatedClass;
    }

    public Class<? extends Cell> getCellType() {
        return associatedClass;
    }
}
