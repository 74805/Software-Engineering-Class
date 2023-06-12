package Exe2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MediaApp implements App {
    private List<Media> media;

    public MediaApp() {
        media = new ArrayList<Media>();
    }

    @Override
    public void add(Object obj) throws Exception {
        try {
            Media m = (Media) obj;
            media.add(m);
        } catch (Exception e) {
            throw new Exception("Invalid object type");
        }
    }

    @Override
    public void run() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.println("\nMedia App");
            System.out.println("1. Add media");
            System.out.println("2. Play media by name");
            System.out.println("3. Play all media");
            System.out.println("4. Exit app");
            System.out.print("Enter your choice: ");

            // Ask for user choice until valid choice is entered
            int userChoice = 0;
            try {
                String line = scanner.nextLine();
                userChoice = Integer.parseInt(line);
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again");
                continue;
            }

            switch (userChoice) {
                case 1:
                    System.out.print("Enter media name: ");
                    String mediaName = scanner.nextLine();

                    // Ask for length until valid length is entered
                    boolean validLength = false;
                    double length = 0;
                    while (!validLength) {
                        System.out.print("Enter music length (in seconds): ");
                        try {
                            String line = scanner.nextLine();
                            length = Double.parseDouble(line);
                            validLength = true;
                        } catch (Exception e) {
                            System.out.println("Invalid length. Please try again.");
                        }
                    }

                    // Ask for media type until valid type is entered
                    boolean validType = false;
                    String mediaType;
                    Media m;
                    while (!validType) {
                        System.out.print("Enter media type (music/video): ");
                        mediaType = scanner.nextLine();

                        switch (mediaType) {
                            case "music":
                                validType = true;

                                m = new Music(mediaName, length);
                                try {
                                    add(m);
                                    System.out.println("\nAdded media successfully");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                break;
                            case "video":
                                validType = true;

                                m = new Video(mediaName, length);
                                try {
                                    add(m);
                                    System.out.println("\nAdded media successfully");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                break;
                            default:
                                System.out.println("Invalid media type. Please try again.");
                        }
                    }

                    break;
                case 2:
                    System.out.print("Enter media name: ");
                    String mediaNameToPlay = scanner.nextLine();

                    playByName(mediaNameToPlay);
                    break;
                case 3:
                    printAll();
                    break;
                case 4:
                    exit();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void playByName(String name) {
        for (Media m : media) {
            if (m.name.equals(name)) {
                m.play();
                return;
            }
        }

        System.out.println("Media not found");
    }

    // printAll plays all media (practicly prints all media)
    @Override
    public void printAll() {
        if (media.size() == 0) {
            System.out.println("No media found");
            return;
        }

        for (Media m : media) {
            m.play();
        }
    }

    public static abstract class Media {
        protected String name;
        protected double length;

        public Media(String name, double length) {
            this.name = name;
            this.length = length;
        }

        public abstract void play();
    }

    public static class Music extends Media {

        public Music(String name, double length) {
            super(name, length);
        }

        @Override
        public void play() {
            System.out.println("Playing song " + name + " for " + length + " seconds");
        }
    }

    public static class Video extends Media {

        public Video(String name, double length) {
            super(name, length);
        }

        @Override
        public void play() {
            System.out.println("Playing video " + name + " for " + length + " seconds");
        }
    }
}
