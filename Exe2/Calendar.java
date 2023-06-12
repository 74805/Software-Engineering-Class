package Exe2;

import java.util.*;

public class Calendar implements App {
    private static List<Event> calendar;
    Scanner In = new Scanner(System.in);

    public Calendar() {
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
            if (day < 1 || day > 31) { //checks if the value of the day is valid
                System.out.println("Invalid day. Please enter a value between 1 and 31.");
                continue;
            } else {
            	counter ++;
            }
            System.out.print("Enter the month: ");
            month = In.nextInt();
            if (month < 1 || month > 12) { //checks if the value of the month is valid
                System.out.println("Invalid month. Please enter a value between 1 and 12.");
                continue;
            } else {
            	counter ++;
            }
            System.out.print("Enter the year: ");
            year = In.nextInt();
            if (year < 0) { //checks if the value of the year is valid
                System.out.println("Invalid year. Please enter a positive value.");
                continue;
            } else {
            	counter ++;
            }
        }
        Date d = new Date(year, month, day);
        return d; // returns the date
    }
    
    public Event receiveEvent(Date d) {
        int minute = 0, counter = 0;
        String desc = "";

        while (counter != 2) {
            System.out.print("Enter the duration: ");
            minute = In.nextInt();
            if (minute < 1 || minute > 60) {
                System.out.println("Invalid duration. Please enter a value between 1 and 60.");
                continue;
            } else {
            	counter ++;
            }
            System.out.print("Enter the client / description: ");
            desc = In.nextLine().trim();
            if (desc.isEmpty()) {
                System.out.println("Description / Client cannot be empty. Please enter a valid description / client.");
                continue;
            } else {
            	counter ++;
            }
        }
        Event event = new Event(d, minute, desc);
        return event;
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
        }
        for (Event event : eventsByDate) {
            System.out.println("Date: " + event.getDate());
            System.out.println("Duration: " + event.getDurationMinutes() + " minutes");
            System.out.println("Description: " + event.getDescription());
            System.out.println();
        }
    }
    
	@Override
	public void run() {
		boolean exit = false;
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
            		Date d = receiveDate();
            		Event eve = receiveEvent(d);
            		try {
						calendar.add(eve);
					} catch (Exception e) {
						System.out.print("Something went wrong...");
					}
        			System.out.print("Added the event!");
            	case 2:
            		d = receiveDate();
            		eve = receiveEvent(d);
					try {
						calendar.remove(eve);
					} catch (Exception e) {
						System.out.print("Something went wrong...");
					}
        			System.out.print("Removed the event!");
            	case 3:
            		d = receiveDate();
            		printEventsByDate(d);
            	case 4:
            		System.out.print("Goobye!");
            		exit(In);
            		exit = true;
            	default:
                    System.out.println("Invalid choice. Please try again.");
            }	
        }
		
	}

	@Override
	public void printAll() {
		if (calendar.size() == 0) {
            System.out.println("No events were found in the calendar.");
            return;
        } else {
        	for (Event ev : calendar) {
                ev.toString();
            }
        }
	}
	
	public class Event extends Calendar {
		private Date date;
	    private int durationMinutes;
	    private String description;

	    public Event(Date date, int durationMinutes, String description) {
	    	
	        this.date = date;
	        this.durationMinutes = durationMinutes;
	        this.description = description;
	    }

	    public Date getDate() {
	        return date;
	    }

	    public int getDurationMinutes() {
	        return durationMinutes;
	    }

	    public String getDescription() {
	        return description;
	    }
	    
	    public void setDate(Date date) {
			this.date = date;
		}

		public void setDurationMinutes(int durationMinutes) {
			this.durationMinutes = durationMinutes;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	    
	    public String toString() {
	    	return date.toString() + ", duration: " + durationMinutes + ", description / client: " + description;
	    }
	}
	

	public class Date extends Calendar {
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
	
}
