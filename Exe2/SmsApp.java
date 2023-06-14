package Exe2;

import java.util.ArrayList;
import java.util.Iterator;

public class SmsApp implements App {
    ArrayList<Chat> myChat;

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

    @Override
    public void run()
    {
        printAll();
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
