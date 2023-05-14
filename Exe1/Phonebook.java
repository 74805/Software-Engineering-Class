package Exe1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
            res += "[" + (i + 1) + "] " + this.contacts.get(i).toString();
        }
        return res;
    }

    public void searchContact(String name) {
        int c = 0;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                System.out.print(contacts.get(i));
                c++;
            }
        }

        if (c == 0) {
            System.out.println("There's no contact with the name " + name + ".");
        }
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
                if (contacts.get(i).getName().equals(contacts.get(j).getName())) {
                    contacts.remove(j);
                    j--;
                }
            }
        }
    }
}
