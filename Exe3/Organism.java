package Exe3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Exe3.Cells.Cell;
import Exe3.Cells.OrganismCells.OrganismCell;

public class Organism {
    private List<OrganismCell> cells;
    private int maxX = 100;
    private int minX= -1;
    private int maxY = 100;
    private int minY = -1;
    private int damage;
    private int energy; // how much food the organism ate
    private boolean alive; //is the organism alive?


    // lifespan is calculated by multiplying the number of cells by the
    // hyperparameter Lifespan Multiplier
    private static final int lifespanMultiplier = 100;
    
    // age is the amout of ticks that have passed since the organism was created
    private int age;

    public Organism() {
        this.cells = new ArrayList<OrganismCell>();
        for(Cell cell: cells){
            if(cell.getX()> maxX) maxX = cell.getX();
            else if(cell.getX()< minX) minX = cell.getX();
            if(cell.getY()> maxY) maxY = cell.getY();
            else if(cell.getY()< minY) minY = cell.getY();
        }
        this.damage = 0;
        this.age = 0;
        this.energy = 0;
        this.alive = true; //in the boeard we maust check if organism is alive!
    }

    public List<OrganismCell> getCells() {
        return cells;
    }

    public void addCell(OrganismCell cell) {
        cells.add(cell);
        if (cell.getX() > maxX) maxX = cell.getX();
        else if (cell.getX()<minX) minX = cell.getX();
        if (cell.getY() > maxY) maxY = cell.getY();
        else if (cell.getY()<minY) minY = cell.getY();
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
        this.alive = false;
    }

    public boolean isAdjacent(Cell cell) {
        for (OrganismCell organismCell : cells) {
            if (organismCell.isAdjacent(cell)) {
                return true;
            }
        }

        return false;
    }

    public int getmaxX(){
        return maxX;
    }

    public int getmaxY(){
        return maxY;
    }

    public int getminX(){
        return minX;
    }

    public int getminY(){
        return minY;
    }



    public void produce(){
        // pick a random location: up/ down/ right/ left
        // if the same shape of the organism shifted to the above direction is empty -produce
        //else- if there is an organism there, add energy for every cell in the shape but
        // if there was an empty cell it would become a food cell
        // when we produce, we copy the cell as is but in a diiferent direction. every cell has
        // 10 percent chance to be randomly mutate to different kind of cell
        // if we were anout to leave the borders- produce in the oposite direction
        //TODO
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        switch(randomNumber){
            case 0: //up
                if (2*maxY - minY +1 >=30) 
                {
                    // height overflow
                }
                else{

                }
                break;
            case 1: //down
                break;
            case 2: //right
                break;
            case 3: //left
                break;
        }
    }
}
