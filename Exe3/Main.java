package Exe3;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create frontend with swing
        JFrame frame = new JFrame("Life Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        Game game = new Game(frame);
        // game.play();
    }
}
