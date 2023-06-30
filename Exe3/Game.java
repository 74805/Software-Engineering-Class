package Exe3;

import javax.swing.JFrame;

public class Game {
    private JFrame frame;
    private Board board;

    public Game(JFrame frame) {
        board = new Board();
        this.frame = frame;
    }

    // public void play() {
    // board.display();
    // }

}
