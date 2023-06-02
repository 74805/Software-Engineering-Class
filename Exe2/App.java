package Exe2;

import java.util.Scanner;

interface App {
    public void add(Object obj) throws Exception;

    public void run();

    public void printAll();

    default public void exit(Scanner scanner) {
        scanner.close();
        System.out.println("Exiting App...");
    }
}
