package Exe2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SmsApp implements App {
    private ArrayList<Chat> myChat;
    private static Phonebook myPhonebook;

    public static void setPhoneBook(Phonebook phonebook) {
        myPhonebook = phonebook;
    }

    public void removeChats() {
        for (Chat chat : myChat) {
            if (myPhonebook.searchContact(chat.getContact().getName()) == null) {
                myChat.remove(chat);
            }
        }
    }

    public SmsApp() {
        myChat = new ArrayList<>();
    }

    // 1
    @Override
    public void add(Object obj) throws Exception {
        try {
            Chat cht = (Chat) obj;
            myChat.add(cht);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // 2
    public void deleteConv(String name) throws Exception {
        try {
            myChat.remove(searchChat(name));
        } catch (Exception e) {
            throw new Exception("couldn't delete chat");
        }
    }

    // 3
    public void printSpecific(String name) {
        try {
            System.out.println(searchChat(name).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 4
    public void printContactBySentence(String sentence) {
        Iterator<Chat> iterator = myChat.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Chat element = iterator.next();
            if (element.isSentenceInChat(sentence))
                System.out.println(element.getContact().getName());
        }
        if (!found)
            System.out.println("No chat contain this sentence");
    }

    // 5
    @Override
    public void printAll() {
        System.out.println("All chats:");
        if (myChat.isEmpty()) {
            System.out.println("No chats");
        }

        Iterator<Chat> iterator = myChat.iterator();
        while (iterator.hasNext()) {
            Chat element = iterator.next();
            System.out.println(element);
        }
    }

    public void run() {
        removeChats();

        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("\nSMS App");
            System.out.println("1. Add chat with contact");
            System.out.println("2. Delete chat with contact");
            System.out.println("3. Print a chat");
            System.out.println("4. Search a sentance");
            System.out.println("5. Print all chats");
            System.out.println("6. Exit app");
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
                    System.out.println("Choose a contact");
                    String name = scanner.nextLine();
                    if (myPhonebook.searchContact(name) == null) {
                        System.out.println("Contact not found");
                        break;
                    }

                    Contact c = myPhonebook.searchContact(name);

                    System.out.println("What would you like to add to the chat?");
                    String text = scanner.nextLine();

                    Chat chat = searchChat(name);
                    if (chat == null) {
                        try {
                            chat = new Chat(c, text);
                            add(chat);
                            System.out.println("Chat added");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    } else {
                        chat.addText(text);
                    }

                    break;
                case 2:
                    System.out.print("Which chat would you like to delete? (input contact name)\n");
                    String name2 = scanner.nextLine();
                    try {
                        deleteConv(name2);
                        System.out.println("Chat deleted");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("What chat would you like to print? (input contact name)");
                    String name3 = scanner.nextLine();
                    printSpecific(name3);
                    break;
                case 4:
                    System.out.println("What sentence would you like to search?");
                    String sentence = scanner.nextLine();
                    System.out.println("\nThe sentence was found in chats with the following contacts:");
                    printContactBySentence(sentence);
                    break;
                case 5:
                    printAll();
                    break;
                case 6:
                    exit();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public Chat searchChat(String name) {
        Iterator<Chat> iterator = myChat.iterator();
        while (iterator.hasNext()) {
            Chat element = iterator.next();
            if (element.getContact().getName().equals(name)) {
                return element;
            }
        }

        return null;
    }

    public class Chat {
        private Contact myContact;
        private ArrayList<String> myText;

        public Chat(Contact c) {
            myContact = c;
            myText = new ArrayList<>();
        }

        public Chat(Contact c, String text) {
            myContact = c;

            myText = new ArrayList<>();
            myText.add(text);
        }

        public void addText(String text) {
            myText.add(text);
        }

        @Override
        public String toString() {
            return "Contact:" + myContact.toString() + "Chat:" + allText();
        }

        public String allText() {
            String allChat = "";
            Iterator<String> iterator = this.myText.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                allChat += element + "\n";
            }
            return allChat;
        }

        public Contact getContact() // protect
        {
            return new Contact(myContact);
        }

        public ArrayList<String> getText() {
            return myText;
        }

        public void deleteChat() {
            myText.clear();
        }

        public boolean isSentenceInChat(String sentence) {
            Iterator<String> iterator = myText.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                if (element.contains(sentence)) {
                    return true;
                }
            }
            return false;
        }

    }
}
