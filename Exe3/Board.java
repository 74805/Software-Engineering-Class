package Exe3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import javax.swing.JPanel;

import Exe3.Cells.Cell;
import Exe3.Cells.EmptyCell;
import Exe3.Cells.FoodCell;
import Exe3.Cells.OrganismCells.KillerCell;
import Exe3.Cells.OrganismCells.OrganismCell;
import Exe3.Cells.OrganismCells.ProducerCell;

public class Board {
    private Cell[][] cells;
    private List<Organism> organisms;
    //private click;

    public Board(int rows, int cols, Consumer<Cell> clickHandler) {
        organisms = new ArrayList<Organism>();
        cells = new Cell[rows][cols];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new EmptyCell(i, j, clickHandler);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public List<Cell> getAdjacentCells(Cell curCell)
    {
        List<Cell> adjacentCells = new ArrayList<>();
        for(Cell[] row:cells){
            for(Cell cell:row){
                if(curCell.isAdjacent(cell)) adjacentCells.add(cell);
            }
        }
        return(adjacentCells);
    }

    public void addCell(Cell cell, int x, int y) {
        cells[x][y] = cell;
    }

    public void replaceCell(Cell cell, int x, int y) {
        cells[x][y] = cell;
    }

    public void display(JPanel panel) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].display(panel);
            }
        }
    }

    public void update() {
        // TODO
    }

    public void disable() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].disable();
            }
        }
    }

    public void enable() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].enable();
            }
        }
    }

    public void reset(JPanel panel, Consumer<Cell> clickHandler) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (!(cells[i][j] instanceof EmptyCell)) {
                    int index = panel.getComponentZOrder(cells[i][j].getButton());
                    panel.remove(cells[i][j].getButton());

                    cells[i][j] = new EmptyCell(i, j, clickHandler);
                    panel.add(cells[i][j].getButton(), index);
                }
            }
        }
    }

    // if the cell is adjacent to an organism, add it to the organism, Otherwise,
    // create a new organism
    public void addToOrganism(OrganismCell cell) {
        Organism adjacentOrganism = null;
        for (int i = 0; i < organisms.size(); i++) {
            Organism organism = organisms.get(i);
            if (organism.isAdjacent(cell)) {
                // if the cell is adjacent to more than one organism, merge them
                if (adjacentOrganism == null) {
                    organism.addCell((OrganismCell) cell);
                    adjacentOrganism = organism;
                } else {
                    adjacentOrganism.merge(organism);
                    organisms.remove(organism);
                    i--;
                }
            }
        }

        // if the cell is adjacent to an organism, add it to the organism
        if (adjacentOrganism != null) {
            return;
        }

        Organism organism = new Organism();
        organism.addCell((OrganismCell) cell);
        organisms.add(organism);
    }

    public void produceOrganism(Organism org, int direction){
        if(2*org.getmaxY() -org.getminY() +1 >=30) direction++;// if the produce will go too high - change
        if(2*org.getminY() -org.getmaxY() -1 <0) direction--;// if the produce will go too low - change
        if(2*org.getmaxX() -org.getminX() +1 >=40) direction++;// if the produce will go too right - change
        if(2*org.getminX() -org.getmaxX() -1 <0) direction--;// if the produce will go too left - change
        switch(direction){
            case 0: //up
                boolean isEmptyPlace = true;
                for(Cell cell:org.getCells()){
                    int x = cell.getX();
                    int y = cell.getY();
                    y+= 2*org.getmaxY() -org.getminY() +1;
                    if(!(this.cells[x][y] instanceof EmptyCell)) isEmptyPlace = false;
                }
                if(isEmptyPlace){
                    for(Cell cell:org.getCells()){
                        int x = cell.getX();
                        int y = cell.getY();
                        y+= 2*org.getmaxY() -org.getminY() +1;
                        Random random = new Random();
                        int muatation = random.nextInt(20);
                        if(muatation == 1){ //10% chance for mutation
                            FoodCell food_cell = new FoodCell(cell);
                            replaceCell(food_cell, x, y);
                        }
                        else if(muatation ==2){
                            ProducerCell producerCell = new ProducerCell(cell);
                            replaceCell(producerCell, x, y);
                        }
                        else if(muatation ==3){
                            KillerCell killerCell = new KillerCell(cell);
                            replaceCell(killerCell, x, y);
                        }
                        else{
                            replaceCell(cell, x, y);
                        }   
                }
                }
                else{
                    for(Cell cell:org.getCells()){
                        int x = cell.getX();
                        int y = cell.getY();
                        y+= 2*org.getmaxY() -org.getminY() +1;
                        if(this.cells[x][y] instanceof EmptyCell) {
                            FoodCell food_cell = new FoodCell((EmptyCell)cells[x][y]);
                            replaceCell(food_cell, x, y);
                        }
                        else if(this.cells[x][y] instanceof OrganismCell) {
                            ((OrganismCell) this.cells[x][y]).getOraganism().addEnergy();
                        }
                }
                }
                break;
            case 1: //down TODO
                break;
            case 2: //right
                break;
            case 3: //left
                break;
        }
    }

    public void produceFood(ProducerCell prodCell){
        //TODO
    }
}
