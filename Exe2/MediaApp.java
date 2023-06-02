package Exe2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MediaApp {
    private List<Media> media;

    public MediaApp() {
        media = new ArrayList<Media>();
    }

    public void add(Media m) {
        media.add(m);
    }

    public void run() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.println("Media App");
            System.out.println("1. Add media");
            System.out.println("2. Play media");
            System.out.println("3. Stop media");
            System.out.println("4. Exit app");
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.print("Enter media name: ");
                    String mediaName = scanner.next();

                    System.out.print("Enter music length: ");
                    double length = scanner.nextDouble();

                    boolean validType = false;
                    String mediaType;
                    Media m;
                    while (!validType) {
                        System.out.print("Enter media type (music/video): ");
                        mediaType = scanner.next();

                        switch (mediaType) {
                            case "music":
                                validType = true;

                                m = new Music(mediaName, length);
                                add(m);

                                break;
                            case "video":
                                validType = true;

                                m = new Video(mediaName, length);
                                add(m);

                                break;
                            default:
                                System.out.println("Invalid media type. Please try again.");
                        }
                    }

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    public void runByName(String name) {
        for (Media m : media) {
            if (m.name.equals(name)) {
                m.run();
            }
        }
    }

    // printAll runs all media (practicly prints all media)
    public void printAll() {
        for (Media m : media) {
            m.run();
        }
    }

    public void exit() {
        // Exit the application
    }

    public static abstract class Media {
        protected String name;
        protected double length;

        public void run() {
            // Run the media
        }
    }

    public static class Video extends Media {

        public Video(String name, double length) {
            this.name = name;
            this.length = length;
        }
    }

    public static class Music extends Media {

        public Music(String name, double length) {
            this.name = name;
            this.length = length;
        }
    }
}
