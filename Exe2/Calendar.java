package Exe2;

import java.util.*;

public class Calendar {
	private static List<Event> events;

    public Calendar() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) { //adds an event to the calendar
    	if (event == null) { //checks if the event is null
    		throw new IllegalArgumentException("Event is not valid, try again.");
    	}
        events.add(event); //add the event if all the requirements are satisfied.
    }

    public void deleteEvent(Event event) { //deletes an event to the calendar
    	if (event == null) { //checks if the event is null
    		throw new IllegalArgumentException("Event is not valid, try again.");
    	}
    	if (!events.contains(event)) { //checks if the event is in the calendar 
            throw new IllegalArgumentException("Event does not exist in the calendar.");
        }
        events.remove(event); //delete the event if all the requirements are satisfied.
    }

    public static List<Event> getEventsByDate(Date date) { //return an event from the calendar
        List<Event> eventsByDate = new ArrayList<>(); //stores all the events in the specific date
        for (Event event : events) {
            if (event.getDate().equals(date)) { //checks if the date matches the specific date
                eventsByDate.add(event);
            }
        }
        return eventsByDate;
    }

    public static void printEventsByDate(Date date) {
        List<Event> eventsByDate = getEventsByDate(date); //takes the list from the function getEventsByDate
        if (eventsByDate.isEmpty()) { //if the date has no events / meetings
            System.out.println("No events scheduled for the specified date.");
            return;
        }
        for (Event event : eventsByDate) {
            System.out.println("Date: " + event.getDate());
            System.out.println("Duration: " + event.getDurationMinutes() + " minutes");
            System.out.println("Description: " + event.getDescription());
            System.out.println();
        }
    }
    
    @SuppressWarnings("deprecation")
	public static void main (String[] args) {
    	boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = new Calendar();
        
        while (!exit) {
        	System.out.println("Welcome to the Calendar App! \n");
        	System.out.print("Enter your choice: \n");
          System.out.println("[1] Add an event. \n");
          System.out.println("[2] Delete an event. \n");
          System.out.println("[3] Print all the events in a specific date. \n");
          System.out.println("[4] Print all the events with a specific client. \n");
          System.out.println("[5] Detect collisions. \n");
          System.out.println("[6] Print all the events. \n");
          System.out.println("[7] Exit. \n");
            
            int choice = 0;
            try {
            	choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again");
                continue;
            }
            
            switch (choice) {
            	case 1:
            		int day, month, year, minute = 0;
            		String desc = "";
            		System.out.print("Enter the day: ");
            		day = scanner.nextInt();
            		System.out.print("Enter the month: ");
            		month = scanner.nextInt();
            		System.out.print("Enter the year: ");
            		year = scanner.nextInt();
            		System.out.print("Enter the duration: ");
            		minute = scanner.nextInt();
            		System.out.print("Enter the client / description: ");
            		desc = scanner.nextLine();
            		Date d = new Date(year, month, day);
            		Event event = new Event(d, minute, desc);
            		calendar.addEvent(event);
            		System.out.print("Added the event!");
            	case 2:
            		System.out.print("Enter the day: ");
            		day = scanner.nextInt();
            		System.out.print("Enter the month: ");
            		month = scanner.nextInt();
            		System.out.print("Enter the year: ");
            		year = scanner.nextInt();
            		System.out.print("Enter the duration: ");
            		minute = scanner.nextInt();
            		System.out.print("Enter the client / description: ");
            		desc = scanner.nextLine();
            		d = new Date(year, month, day);
            		event = new Event(d, minute, desc);
            		calendar.addEvent(event);
            		System.out.print("Added the event!");
            	case 3:
            		System.out.print("Enter the day: ");
            		day = scanner.nextInt();
            		System.out.print("Enter the month: ");
            		month = scanner.nextInt();
            		System.out.print("Enter the year: ");
            		year = scanner.nextInt();
            		d = new Date(year, month, day);
            		System.out.println();
            		printEventsByDate(d);
            }	
        }
    }
}
