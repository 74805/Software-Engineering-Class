package Exe2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phone {
    private List<App> apps;

    public Phone() {
        apps = new ArrayList<App>();

        // Add the four apps
        // apps.add(new ContactsApp());
        // apps.add(new SMSApp());
        // apps.add(new CalenderApp());
        apps.add(new MediaApp());
    }

    public void mainMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nPhone");
            System.out.println("Choose one of the options below:");
            System.out.println("1. Run Contacts App");
            System.out.println("2. Run SMS App");
            System.out.println("3. Run Calender App");
            System.out.println("4. Run Media App");
            System.out.println("5. Print all apps' content");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            int userChoice = 0;
            try {
                userChoice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again");
                continue;
            }

            switch (userChoice) {
                case 1:
                    apps.get(0).run();
                    break;
                case 2:
                    apps.get(1).run();
                    break;
                case 3:
                    apps.get(2).run();
                    break;
                case 4:
                    apps.get(3).run();
                    break;
                case 5:
                    printAll();
                    break;
                case 6:
                    exit(scanner);
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
                    break;
            }
        }
    }

    public void printAll() {
        System.out.println("Printing all apps");
        for (App app : apps) {
            System.out.println();
            app.printAll();
        }
    }

    public void exit(Scanner scanner) {
        System.out.println("Exiting phone...");
        scanner.close();
    }
}
