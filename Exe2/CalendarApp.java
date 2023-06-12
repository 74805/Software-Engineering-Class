package Exe2;

import java.util.*;

public class CalendarApp implements App {
    private static List<Event> calendar;
    private Phonebook phoneBook;
    public void setPhonebook(Phonebook phoneBook) {
        this.phoneBook = phoneBook;
    }
    Scanner In = new Scanner(System.in);
    
    public int getSize() {
    	return calendar.size();
    }
    
    public Event getEvent(int i) {
    	return calendar.get(i);
    }
    
    public CalendarApp() {
    	calendar = new ArrayList<Event>();
    }
    
    @Override
    public void add(Object obj) throws Exception {
		try {
            Event eve = (Event) obj;
            if (eve == null) { //checks if the event is null
        		throw new IllegalArgumentException("Event is not valid, try again.");
        	}
            calendar.add(eve); //add the event if all the requirements are satisfied.
        } catch (Exception e) {
            throw new Exception("Invalid object type");
        }
	}
    
    public void remove(Object obj) throws Exception {
		try {
            Event eve = (Event) obj;
            if (eve == null) { //checks if the event is null
        		throw new IllegalArgumentException("Event is not valid, try again.");
        	}
            if (!calendar.contains(eve)) { //checks if the event is in the calendar 
                throw new IllegalArgumentException("Event does not exist in the calendar.");
            }
            calendar.remove(eve); //add the event if all the requirements are satisfied.
        } catch (Exception e) {
            throw new Exception("Invalid object type");
        }
	}
    
    public Date receiveDate() {
        int day = 0, month = 0, year = 0, counter = 0;

        while (counter != 3) {
        	counter = 0;
            System.out.print("Enter the day: ");
            day = In.nextInt();
            if (day < 1 || day > 31) { //checks the validity of the input
                System.out.println("Invalid day. Please enter a value between 1 and 31.");
                continue;
            } else {
            	counter ++;
            }
            System.out.print("Enter the month: ");
            month = In.nextInt();
            if (month < 1 || month > 12) {  //checks the validity of the input
                System.out.println("Invalid month. Please enter a value between 1 and 12.");
                continue;
            } else {
            	counter ++;
            }
            System.out.print("Enter the year: ");
            year = In.nextInt();
            if (year < 0) {  //checks the validity of the input
                System.out.println("Invalid year. Please enter a positive value.");
                continue;
            } else {
            	counter ++;
            }
        }
        Date d = new Date(year, month, day);  //if everything has met the requirements, a new Date object will be made.
        return d; //returns the Date object.
    }
    
    public MeetingEvent receiveEventWithContact(Date d, Phonebook.Contact con) {
        int minute = 0, counter = 0;
        
        while (counter != 1) {
            System.out.print("Enter the duration: ");
            minute = In.nextInt();
            if (minute < 1 || minute > 60) { //checks the validity of the input 
                System.out.println("Invalid duration. Please enter a value between 1 and 60.");
                continue;
            } else {
            	counter ++;
            }
        }
        MeetingEvent event = new MeetingEvent(d, minute, con); //if everything has met the requirements, a new MeetingEvent object will be made.
        return event; //return the MeetingEvent object.
    }
    
    public EventWithoutMeeting receiveEventNoContact (Date d, String description) {
        int minute = 0, counter = 0;
        
        while (counter != 1) {
            System.out.print("Enter the duration: ");
            minute = In.nextInt();
            if (minute < 1 || minute > 60) { //checks the validity of the input 
                System.out.println("Invalid duration. Please enter a value between 1 and 60.");
                continue;
            } else {
            	counter ++;
            }
        }
        EventWithoutMeeting event = new EventWithoutMeeting(d, minute, description); //if everything has met the requirements, a new MeetingEvent object will be made.
        return event; //return the MeetingEvent object.
    }

    public static List<Event> getEventsByDate(Date date) { //return an event from the calendar
        List<Event> eventsByDate = new ArrayList<>(); //stores all the events in the specific date
        for (Event event : calendar) {
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
        } else {
        	for (Event event : eventsByDate) {
        		if (event instanceof MeetingEvent) {
        			MeetingEvent mEvent = (MeetingEvent) event;
        			System.out.println("Date: " + mEvent.getDate());
    	            System.out.println("Duration: " + mEvent.getDurationMinutes() + " minutes");
    	            System.out.println("Contact (Name / Number): " + mEvent.getContact().getName() + mEvent.getContact().getPhoneNumber());
    	            System.out.println();
        		}
        		if (event instanceof EventWithoutMeeting) {
        			EventWithoutMeeting nEvent = (EventWithoutMeeting) event;
        			System.out.println("Date: " + nEvent.getDate());
    	            System.out.println("Duration: " + nEvent.getDurationMinutes() + " minutes");
    	            System.out.println("Short Description: " + nEvent.getDescription());
    	            System.out.println();
        		}
        	}
        }
    }
    
	@Override
	public void run() {
		boolean exit = false;
        CalendarApp calendar = new CalendarApp();
        
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
            int meetingChoice = 0;
            String contactName = "", shortDescription = "";
            Phonebook.Contact c = null;
            Date d;
            boolean temp = false; 
            
            while (true) {
                System.out.println("Your choice is: ");
                choice = In.nextInt();
                if (choice < 1 || choice > 7) {
                    System.out.println("Invalid Input. Try Again..");
                } else {
                    break;
                }
            }
            
            switch (choice) {
            	case 1:
            		System.out.print("Choose the type of meeting to add: \n");
            		System.out.print("[1] Meeting with a Contact. \n");
            		System.out.print("[1] An event with no meeting. \n");
            		System.out.println("Your choice is: ");
            		meetingChoice = In.nextInt(); 
            		if (meetingChoice != 1 && meetingChoice != 2) {
            			System.out.println("Invalid Input. Try Again..");
            			break;
            		} else if (meetingChoice == 1) {
            			System.out.print("Enter the name of the contact: ");
            			contactName = In.nextLine();
            			temp = false;
            			for (int i = 0; i < phoneBook.getSize(); i++) {
            				if (phoneBook.getContact(i).getName().equals(contactName)) {
            					temp = true;
            					c = phoneBook.getContact(i);
            					break;
            			}
            			if (temp == false) {
	            				System.out.print("There's no contact with the name " + contactName + ", try again.");
	            				break;
            				}
            			}
            			
            			d = receiveDate();
                		Event eve = receiveEventWithContact(d, c);
    	            		try {
    							calendar.add(eve);
    						} catch (Exception e) {
    							System.out.print("Something went wrong...");
    					}
            		} else if (meetingChoice == 2) {
            			System.out.print("Enter a short description about the event: ");
            			shortDescription = In.nextLine();    			
            			d = receiveDate();
                		Event eve = receiveEventNoContact(d, shortDescription);
    	            		try {
    							calendar.add(eve);
    						} catch (Exception e) {
    							System.out.print("Something went wrong...");
    							break;
    					}
            		}
            		break;
	
            	case 2:
            		System.out.print("Choose the type of meeting to add: \n");
            		System.out.print("[1] Meeting with a Contact. \n");
            		System.out.print("[1] An event with no meeting. \n");
            		System.out.println("Your choice is: ");
            		meetingChoice = In.nextInt(); 
            		if (meetingChoice != 1 && meetingChoice != 2) {
            			System.out.println("Invalid Input. Try Again..");
            			break;
            		} else if (meetingChoice == 1) {	
            			System.out.print("Enter the name of the contact: ");
            			contactName = In.nextLine();
            			for (int i = 0; i < calendar.getSize(); i++) {
            				if (calendar.getEvent(i) instanceof MeetingEvent) { //looks for MeetingEvent type events
                		        MeetingEvent mEvent = (MeetingEvent) calendar.getEvent(i);
                		        if (mEvent.getContact().getName().equals(contactName)) {
                		        	d = receiveDate();
                		        	if (mEvent.getDate().equals(d)) {
                		        		temp = true;
                    		        	try {
                    						calendar.remove(mEvent);
                    					} catch (Exception e) {
                    						System.out.print("Something went wrong...");
                    					}
                		        	}
                		        } else {
                		        	System.out.print("No meeting was found.. try again.");
                		        	break;
                		        }
                		    }
            			}
            			if (temp == false) {
            				System.out.print("There's no contact with the name " + contactName + ", try again.");
            				break;
            			}
            			
            		} else if (meetingChoice == 2) {  			
            			d = receiveDate();
            			for (int i = 0; i < calendar.getSize(); i++) {
            				if (calendar.getEvent(i) instanceof EventWithoutMeeting) { //looks for MeetingEvent type events
            					EventWithoutMeeting nEvent = (EventWithoutMeeting) calendar.getEvent(i);
                		        if (nEvent.getEvent(i).getDate().equals(d)) {
                		        	d = receiveDate();
                		        	if (nEvent.getDate().equals(d)) {
                		        		temp = true;
                    		        	try {
                    						calendar.remove(nEvent);
                    					} catch (Exception e) {
                    						System.out.print("Something went wrong...");
                    					}
                		        	}
                		        } else {
                		        	System.out.print("No meeting was found.. try again.");
                		        	break;
                		        }
                		    }
            			}
            			if (temp == false) {
            				System.out.print("There's no contact with the name " + contactName + ", try again.");
            				break;
            			}
            		}
        			System.out.print("Removed the event!");
        			break;
            	case 3:
            		d = receiveDate();
            		printEventsByDate(d);
            	case 7:
            		System.out.print("Goobye!");
            		exit(In);
            		exit = true;
            	default:
                    System.out.println("Invalid choice. Please try again.");
            	}	
        	}	
    	}
	
		public abstract class Event extends CalendarApp {
		    protected Date date;
		    protected int durationMinutes;
		
		    public Event(Date date, int durationMinutes) {
		        this.date = date;
		        this.durationMinutes = durationMinutes;
		    }
		
		    public Date getDate() {
		        return date;
		    }
		
		    public int getDurationMinutes() {
		        return durationMinutes;
		    }
		}
		
		public class MeetingEvent extends Event {
		    private Phonebook.Contact contact;
		
		    public MeetingEvent(Date date, int durationMinutes, Phonebook.Contact contact) {
		        super(date, durationMinutes);
		        this.contact = contact;
		    }
		
		    public Phonebook.Contact getContact() {
		        return contact;
		    }
		
			public String toString() {
		    	return "Meeting Event details: Date: " + date.toString() + ", duration: " + durationMinutes + ", Contact: " + getContact();
		    }
		}
		
		public class EventWithoutMeeting extends Event {
		    private String description;
	
		    public EventWithoutMeeting(Date date, int durationMinutes, String description) {
		        super(date, durationMinutes);
		        this.description = description;
		    }
	
		    public String getDescription() {
		        return description;
		    }
	
		    public String toString() {
		    	return "Event details: Date: " + date.toString() + ", duration: " + durationMinutes + ", description:  " + getDescription();
		    }
		}
		
		public class Date extends CalendarApp {
			private int day;
			private int month;
			private int year;
			
			public Date(int day, int month, int year) {
		        this.day = day;
		        this.month = month;
		        this.year = year;
		    }
			public int getDay() {
				return day;
			}
			public int getMonth() {
				return month;
			}
			public int getYear() {
				return year;
			}
			public void setDay(int day) {
				this.day = day;
			}
			public void setMonth(int month) {
				this.month = month;
			}
			public void setYear(int year) {
				this.year = year;
			}
			@Override
			public String toString() {
				return day + "/" + month + "/" + year;
			}	
		}

		@Override
		public void printAll() {
			// Enter Code Here 
		}	
}
