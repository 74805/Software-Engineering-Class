package Exe2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Iterator;


public class Phonebook implements App {
    protected ArrayList<Contact> contacts;

    public Phonebook() {
        this.contacts = new ArrayList<Contact>();
    }

    

    public void addContact(Contact c) throws Exception {
        if(! inPhoneBook(c.getName())){
           contacts.add(c);
        }
        else {
            throw new Exception("This name was added already");
        }
    }

    public Contact getContact(int index) {
        return new Contact(this.contacts.get(index)); // not by reference
    }

    public int getSize() {
        return this.contacts.size();
    }

    public void removeContact(String name) { 
        Iterator <Contact> iterator = contacts.iterator();
        while(iterator.hasNext()){
            Contact currContact = iterator.next();
            if(currContact.getName().equals(name)){
                contacts.remove(currContact);
                System.out.println("An account with the name " + name
                + " has been deleted.");
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

    public Contact searchContact(String name ) throws Exception {
       Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact currentContact = iterator.next();
            return currentContact;
        }
        throw new Exception("Name not found");
        
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
        ArrayList<Contact> New_contacts = new ArrayList<>();
        for (Contact c : contacts)
        {
            if(!New_contacts.contains(c))
            {
                New_contacts.add(c);
            }
        }
        contacts.clear();
        contacts.addAll(New_contacts);
    }

    public void reverseOrder() {
        Contact temp = new Contact();
        for (int i = 0; i < contacts.size() / 2; i++) {
            try{
                temp.setContact(contacts.get(i).getName(), contacts.get(i).getPhoneNumber());
                contacts.get(i).setContact(contacts.get(contacts.size() - i - 1).getName(),
                contacts.get(contacts.size() - i - 1).getPhoneNumber());
                contacts.get(contacts.size() - i - 1).setContact(temp.getName(), temp.getPhoneNumber());
            }
            catch(Exception e)
            {
                System.out.println("Can not reverse order.");
                continue;
            }
        }
    }

    public void savingPhonebook(String textFileName) throws IOException {
        File phonebookInfo = new File(textFileName);// creat text file
        FileWriter writer = new FileWriter(phonebookInfo, false);// to write
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            writer.write(contact.toString() + "\n");
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

    public boolean inPhoneBook(String name) //new!!!!
    {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equalsIgnoreCase(name)) { // ignore case
                return true;
            }
        }
        return false;
    }

    @Override
    public void run(){
        Scanner In = new Scanner(System.in);

        Phonebook phonebook = new Phonebook();
        System.out.println("[+] Phone Book\n------------");
        try{
        phonebook.addContact(new Contact("Amos", 583696427));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        try{
        phonebook.addContact(new Contact("Yossi", 529723081));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

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
                    System.out.println("Enter The Number of the contact (05 and 8 digit):");
                    int p = In.nextInt();
                    String regEx = "5[0-9]{8}"; 
                    if(Integer.toString((p)).matches(regEx))
                    {
                        Contact temp = new Contact(n, p);
                        try{
                            phonebook.addContact(temp);
                            System.out.println("You added: ");
                            System.out.println(temp);
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    else
                    {
                        System.out.println("Illegal Phone number!");;
                    }
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
                    try{
                        phonebook.searchContact(findName);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    
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

    @Override
    public void add(Object obj) throws Exception {
        try {
            Contact m = (Contact) obj;
            this.addContact(m);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public void printAll() {
        Iterator<Contact> iterator = this.contacts.iterator();
        while (iterator.hasNext()) {
            Contact element = iterator.next();
            System.out.println(element); //overrides tostring
    }
}
}



