package Exe2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Exe2.MediaApp.Music;
import Exe2.MediaApp.Video;

public class SmsApp implements App {
    private ArrayList<Chat> myChat;
    private Phonebook myPhonebook;

    public SmsApp(Phonebook phonebook)
    {
        myPhonebook = phonebook;
        myChat = new ArrayList<>();
    }

    //1
    @Override
    public void add(Object obj) throws Exception
    {
        try {
            Chat cht = (Chat) obj;
            myChat.add(cht);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //2
    public void deleteConv(Contact c) throws Exception
    {
        searchChat(c).deleteChat();
    }

    //3
    public void printSpecific(Contact c) throws Exception
    {
        System.out.println(searchChat(c).allText());
    }

    //4
    public void printContactBySentence(String sentence){
        Iterator<Chat> iterator = myChat.iterator();
        boolean found = false;
        while(iterator.hasNext()){
            Chat element = iterator.next();
            if(element.isSentenceInChat(sentence)) System.out.println(element.getContact().getName());
        }
        if(!found) System.out.println("No chat contain this sentence");
    }

    //5
    @Override 
    public void printAll(){
        Iterator<Chat> iterator = this.myChat.iterator();
        while (iterator.hasNext()) {
            Chat element = iterator.next();
            System.out.println(element);
        }   
    }

    //run and haspaa
    @Override
    public void run()
    {
        boolean exit = false;
        try (Scanner scanner = new Scanner(System.in)) {
            while (!exit) {
                System.out.println("\nSMS App");
                System.out.println("1. Add messag to contact");
                System.out.println("2. Delete Contact");
                System.out.println("3. Print a contact");
                System.out.println("4. Get all the contacts with a user chosen sentance");
                System.out.println("5. Print all chats");
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
                        System.out.println("Choose a contact to send him message:");
                        String name_contact;
                        try {
                            String line = scanner.nextLine();
                            name_contact = line;
                        } catch (Exception e) {
                         System.out.println("Invalid choice, please try again");
                         continue;
                        }
                        try{
                            
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
    }


    public Chat searchChat(Contact c) throws Exception
    {
        Iterator<Chat> iterator = myChat.iterator();
        while(iterator.hasNext()){
            Chat element = iterator.next();
            if (element.getContact().equals(c))
            {
                return element;
            }
        }
        throw new Exception("Contact not found");
    }



public class Chat{
    private Contact myContact;
    private ArrayList<String> myText;
    public Chat(Contact c, String text)
    {
        myContact = c;
        myText.add(text);
    }
    @Override
    public String toString()
    {
        return "Contact:" + myContact.toString() + "Chat:" + allText();
    }

    public String allText(){
        String allChat = "";
        Iterator<String> iterator = this.myText.iterator();
        while(iterator.hasNext())
        {
            String element = iterator.next();
            allChat.concat(element + "\n");
        }
        return allChat;
    }

    public Contact getContact() //protect
    {
        return new Contact(myContact);
    }

    public ArrayList<String> getText()
    {
        return myText;
    }

    public void deleteChat()
    {
        myText.clear();
    }

    public boolean isSentenceInChat(String sentence){
        Iterator<String> iterator = myText.iterator();
        while(iterator.hasNext()){
            String element = iterator.next();
            if (element.contains(sentence))
            {
                return true;
            }
        }
        return false;
    }

    }
}
