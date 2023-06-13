package Exe2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Phonebook {
    private ArrayList<Contact> contacts;

    public Phonebook() {
        this.contacts = new ArrayList<Contact>();
    }

    public void addContact(Contact c) {
        this.contacts.add(c);
    }

    public Contact getContact(int index) {
        return this.contacts.get(index);
    }

    public int getSize() {
        return this.contacts.size();
    }

    public void removeContact(String name) {
        for (int i = 0; i < this.contacts.size(); i++) {
            if (this.contacts.get(i).getName().equals(name)) {
                this.contacts.remove(i);
                System.out.println("An account with the name " + name
                        + " has been deleted.");
                return;
            }
        }
        System.out.println("No account with the name "
                + name + " has been found.");
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.contacts.size(); i++) {
            res += "[" + (i + 1) + "] " + this.contacts.get(i);
        }
        return res;
    }

    public Contact searchContact(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                // return the first one because name is unique
                return contacts.get(i);
            }
        }

        return null;
    }

    public void sortByName() {
        Collections.sort(contacts, new Comparator<Contact>() {
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
    }

    public void sortByNumber() {
        Collections.sort(contacts, new Comparator<Contact>() {
            public int compare(Contact c1, Contact c2) {
                return c2.getPhoneNumber() - c1.getPhoneNumber();
            }
        });
    }

    public void removeDuplicates() {
        for (int i = 0; i < contacts.size(); i++) {
            for (int j = i + 1; j < contacts.size(); j++) {
                if (contacts.get(i).getName().equals(contacts.get(j).getName())
                        && contacts.get(i).getPhoneNumber() == contacts.get(j).getPhoneNumber()) {
                    contacts.remove(j);
                    j--;
                }
            }
        }
    }

    public void reverseOrder() {
        Contact temp = new Contact();
        for (int i = 0; i < contacts.size() / 2; i++) {
            temp.setContact(contacts.get(i).getName(), contacts.get(i).getPhoneNumber());
            contacts.get(i).setContact(contacts.get(contacts.size() - i - 1).getName(),
                    contacts.get(contacts.size() - i - 1).getPhoneNumber());
            contacts.get(contacts.size() - i - 1).setContact(temp.getName(), temp.getPhoneNumber());
        }
    }

    public void savingPhonebook(String textFileName) throws IOException {
        File phonebookInfo = new File(textFileName);// creat text file
        FileWriter writer = new FileWriter(phonebookInfo, false);// to write
        for (int i = 0; i < contacts.size(); i++) { // run all over the contacts in the phonebook
            writer.write(contacts.get(i).toString());
        }
        writer.close();
    }

    public void loadingPhonebook(String textFileName) throws IOException {
        File myObj = new File(textFileName);
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {
            String contactString = myReader.nextLine();

            String name = contactString.split(" - ")[0];
            int phone = Integer.parseInt(contactString.split(" - ")[1]);

            Contact t = new Contact(name, phone);
            contacts.add(t);
        }

        myReader.close();
    }
}
