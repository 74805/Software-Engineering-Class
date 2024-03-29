// Group serial number: 12

package Exe1;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner In = new Scanner(System.in);

        Phonebook phonebook = new Phonebook();
        System.out.println("[+] Phone Book\n------------");

        phonebook.addContact(new Contact("Amos", 583696427));
        phonebook.addContact(new Contact("Yossi", 529723081));

        boolean flag = true;
        while (flag) {

            System.out.println("\nChoose one of the following options: ");
            System.out.println("[1] Add Contact To Your Phonebook. \n"
                    + "[2] Delete A Contact From Your Phonebook. \n"
                    + "[3] Print Out All Of Your Saved Contacts. \n"
                    + "[4] Search For A Contact (By Name). \n"
                    + "[5] Sort All Of Your Contacts Alphabetically (A-Z). \n"
                    + "[6] Sort All Of Your Contacts By Number. \n"
                    + "[7] Remove Duplicates From Your Phonebook (By Phone Number and Name). \n"
                    + "[8] Sort All Your Contacts In Reverse Order. \n"
                    + "[9] Save All Your Contacts In A .txt file. \n"
                    + "[10] Load All Contacts From A .txt file and Add To Your current list. \n"
                    + "[11] Exit. \n");

            int choise;
            while (true) {
                System.out.println("Your choice is: ");
                choise = In.nextInt();
                if (choise < 1 || choise > 11) {
                    System.out.println("Invalid Input. Try Again..");
                } else {
                    break;
                }
            }
            In.nextLine();

            switch (choise) {
                case 1:
                    // Add Contact To Your Phonebook.
                    System.out.println("Enter The Name of the contact:");
                    String n = In.nextLine();
                    System.out.println("Enter The Number of the contact:");
                    int p = In.nextInt();

                    Contact temp = new Contact(n, p);
                    phonebook.addContact(temp);

                    System.out.println("You added: ");
                    System.out.println(temp);
                    break;
                case 2:
                    // Delete a contact
                    if (phonebook.getSize() == 0) {
                        System.out.println("Your Phonebook is empty.");
                        continue;
                    }

                    System.out.println("Enter The Name of the contact you'd like to delete:");
                    String remName = In.nextLine();

                    phonebook.removeContact(remName);
                    break;
                case 3:
                    // Print all contacts
                    if (phonebook.getSize() == 0) {
                        System.out.println("Your Phonebook is empty.");
                        continue;
                    }

                    System.out.println(phonebook);
                    break;
                case 4:
                    // Search for a contact
                    if (phonebook.getSize() == 0) {
                        System.out.println("Your Phonebook is empty.");
                        continue;
                    }

                    System.out.println("Enter The Name of the contact you'd like to find:");
                    String findName = In.nextLine();

                    phonebook.searchContact(findName);
                    break;
                case 5:
                    // Sort all contacts alphabetically
                    if (phonebook.getSize() == 0) {
                        System.out.println("Your Phonebook is empty.");
                        continue;
                    }

                    phonebook.sortByName();

                    System.out.println("Here's Your Phonebook after sorting it alphabetically (A-Z):");
                    for (int i = 0; i < phonebook.getSize(); i++) {
                        System.out.print(phonebook.getContact(i));
                    }
                    break;
                case 6:
                    // Sort all contacts by number
                    if (phonebook.getSize() == 0) {
                        System.out.println("Your Phonebook is empty.");
                        continue;
                    }

                    phonebook.sortByNumber();

                    System.out.println("Here's Your Phonebook after sorting it by number:");
                    System.out.println(phonebook);
                    break;
                case 7:
                    // Remove duplicates
                    if (phonebook.getSize() == 0) {
                        System.out.println("Your Phonebook is empty.");
                        continue;
                    }

                    phonebook.removeDuplicates();

                    System.out.println("Here's Your Phonebook after removing duplicates:");
                    System.out.println(phonebook);

                    break;
                case 8:
                    // reverse order
                    if (phonebook.getSize() == 0) {
                        System.out.println("Your Phonebook is empty.");
                        continue;
                    }

                    phonebook.reverseOrder();
                    System.out.println("Here's Your Phonebook after reversing its order:");
                    System.out.println(phonebook);
                    break;
                case 9:
                    // save in text file
                    System.out.println("Enter The Name of the file that you want to save your phonbook in: ");
                    String fileName = In.nextLine();

                    try {
                        phonebook.savingPhonebook(fileName);
                        System.out.println("Your Phonebook has been saved successfully.");
                    } catch (IOException e) {
                        System.out.println("Error saving to file: " + e);
                    }
                    break;
                case 10:
                    // load from text file
                    System.out.println("Enter The Name of the file that you want to save your phonbook in: ");
                    String file = In.nextLine();

                    try {
                        phonebook.loadingPhonebook(file);
                        System.out.println("Your Phonebook has been loaded successfully.");
                    } catch (IOException e) {
                        System.out.println("Error loading from file: " + e);
                    }
                    break;
                case 11:
                    System.out.println("Goodbye!\n");

                    flag = false;
                    In.close();
                    break;
            }
        }
    }
}
