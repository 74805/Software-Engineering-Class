package Exe2;

import java.util.*;

public class CalendarApp implements App {
	// Each index in the calendar list represents a date (not neccessarily in
	// chronological order)
	private List<List<Event>> calendar;
	private static Phonebook phoneBook;

	public CalendarApp() {
		calendar = new ArrayList<>(30);

		for (int i = 0; i < 30; i++) {
			calendar.add(new ArrayList<>());
		}
	}

	public static void setPhonebook(Phonebook phoneBook) {
		CalendarApp.phoneBook = phoneBook;
	}

	public int searchDateIndex(Date date) {
		// Search the date in the calendar
		int index = -1;
		for (int i = 0; i < calendar.size(); i++) {
			if (calendar.get(i).isEmpty() || calendar.get(i).get(0).getDate().equals(date)) {
				index = i;
				break;
			}
		}

		return index;
	}

	@Override
	public void add(Object obj) throws Exception {
		try {
			Event eve = (Event) obj;
			if (eve == null) { // checks if the event is null
				throw new IllegalArgumentException("Event is not valid, try again.");
			}

			int index = searchDateIndex(eve.getDate()); // search the date in the calendar

			if (index == -1) { // the calendar is full
				System.out.println("The calendar is full, there is a limit of" + calendar.size()
						+ " dates, remove some events or choose a different date.");
				return;
			}

			calendar.get(index).add(eve); // add the event if all the requirements are satisfied.
		} catch (Exception e) {
			throw new Exception("Invalid object type");
		}
	}

	public void remove(Object obj) throws Exception {
		try {
			Event eve = (Event) obj;
			if (eve == null) { // checks if the event is null
				throw new IllegalArgumentException("Event is not valid, try again.");
			}

			int dateIndex = searchDateIndex(eve.getDate()); // search the date in the calendar
			if (dateIndex == -1 || calendar.get(dateIndex).isEmpty() || !calendar.get(dateIndex).contains(eve)) {
				throw new IllegalArgumentException("Event does not exist in the calendar.");
			}

			calendar.get(dateIndex).remove(eve); // remove the event if all the requirements are satisfied.
		} catch (Exception e) {
			throw new Exception("Invalid object type");
		}
	}

	public Date receiveDate(Scanner In) {
		int day = 0, month = 0, year = 0;

		System.out.println("Enter the date: ");
		while (true) {
			System.out.print("Enter the day: ");
			day = In.nextInt();
			In.nextLine();
			if (day < 1 || day > 30) { // checks the validity of the input
				System.out.println("Invalid day. Please enter a value between 1 and 31.");
				continue;
			}

			System.out.print("Enter the month: ");
			month = In.nextInt();
			In.nextLine();
			if (month < 1 || month > 12) { // checks the validity of the input
				System.out.println("Invalid month. Please enter a value between 1 and 12.");
				continue;
			}

			System.out.print("Enter the year: ");
			year = In.nextInt();
			In.nextLine();
			if (year < 0) { // checks the validity of the input
				System.out.println("Invalid year. Please enter a positive value.");
				continue;
			} else {
				break;
			}
		}

		Date d = new Date(year, month, day); // if everything has met the requirements, a new Date object will be made.
		return d; // returns the Date object.
	}

	public MeetingEvent receiveEventWithContact(Date d, Contact con, Scanner In) {
		int minute = 0;

		while (true) {
			System.out.print("Enter the duration (in miuntes): ");
			minute = In.nextInt();
			if (minute < 1 || minute > 60) { // checks the validity of the input
				System.out.println("Invalid duration. Please enter a value between 1 and 60.");
			} else {
				break;
			}
		}

		MeetingEvent event = new MeetingEvent(d, minute, con); // if everything has met the requirements, a new
																// MeetingEvent object will be made.
		return event; // return the MeetingEvent object.
	}

	public EventWithoutMeeting receiveEventNoContact(Date d, String description, Scanner In) {
		int minute = 0;

		while (true) {
			System.out.print("Enter the duration (in miuntes): ");
			minute = In.nextInt();
			if (minute < 1 || minute > 60) { // checks the validity of the input
				System.out.println("Invalid duration. Please enter a value between 1 and 60.");
			} else {
				break;
			}
		}
		EventWithoutMeeting event = new EventWithoutMeeting(d, minute, description); // if everything has met the
																						// requirements, a new
																						// MeetingEvent object will be
																						// made.
		return event; // return the MeetingEvent object.
	}

	public List<Event> getEventsByDate(Date date) { // return an event from the calendar
		int index = searchDateIndex(date); // search the date in the calendar

		if (index == -1) { // the calendar is full
			return new ArrayList<>(); // return an empty list
		}

		return calendar.get(index); // return the events
	}

	public void printEventsByDate(Date date) {
		List<Event> eventsByDate = getEventsByDate(date); // takes the list from the function getEventsByDate
		if (eventsByDate.isEmpty()) { // if the date has no events / meetings
			System.out.println("No events scheduled for the specified date.");
			return;
		}

		for (Event event : eventsByDate) {
			System.out.println(event.toString()); // print the events
		}
	}

	@Override
	public void run() {
		System.out.println("\nWelcome to the Calendar App!");

		boolean exit = false;
		Scanner In = new Scanner(System.in);

		while (!exit) {
			System.out.print("\nEnter your choice: \n");
			System.out.println("[1] Add an event. \n");
			System.out.println("[2] Delete an event. \n");
			System.out.println("[3] Print all the events in a specific date. \n");
			System.out.println("[4] Print all the events with a specific client. \n");
			System.out.println("[5] Detect collisions. \n");
			System.out.println("[6] Print all the events. \n");
			System.out.println("[7] Exit. \n");

			int choice = 0;
			String shortDescription = "";
			Contact Arik_contact;
			Date d;
			Boolean invalidInput;

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
					System.out.print("Choose the type of event to add: \n");
					System.out.print("[1] Meeting with a Contact. \n");
					System.out.print("[2] An event with no meeting. \n");

					System.out.println("Your choice is: ");
					int meetingChoice = In.nextInt();
					In.nextLine();

					Event eve;
					invalidInput = true;
					while (invalidInput) {
						switch (meetingChoice) {
							case 1:
								System.out.print("\nEnter the name of the contact: ");
								String contactName = In.nextLine();

								// validate that the name contains only letters
								if (!contactName.matches("[a-zA-Z]+")) {
									System.out.print("Invalid name. Try again.");
									break;
								}
								try{
									Arik_contact = phoneBook.searchContact(contactName);
								}catch(Exception e){
									System.out.println(e.getMessage());
									break;
								}

								d = receiveDate(In);
								eve = receiveEventWithContact(d, Arik_contact, In);
								try {
									add(eve);
									System.out.println("Added event successfully");
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}

								invalidInput = false;
								break;
							case 2:
								System.out.print("Enter a short description of one line about the event: ");
								shortDescription = In.nextLine();

								d = receiveDate(In);
								eve = receiveEventNoContact(d, shortDescription, In);
								try {
									add(eve);
									System.out.println("Added event successfully");
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}

								invalidInput = false;
								break;
							default:
								System.out.println("Invalid Input. Try Again..");
								break;
						}
					}
					break;
				case 2:
					System.out.print("Choose the type of meeting to remove: \n");
					System.out.print("[1] Meeting with a Contact. \n");
					System.out.print("[2] An event with no meeting. \n");

					System.out.println("Your choice is: ");
					meetingChoice = In.nextInt();

					invalidInput = true;
					while (invalidInput) {
						List<Event> events;
						boolean eventFound = false;

						d = receiveDate(In);
						switch (meetingChoice) {
							case 1:
								// Assuming with each contact there is no more than one meeting each date
								System.out.print("Enter the name of the contact: ");
								String contactName = In.nextLine();

								events = getEventsByDate(d);

								eventFound = false;
								for (int i = 0; i < events.size(); i++) {
									if (events.get(i) instanceof MeetingEvent) { // looks for MeetingEvent type events
										MeetingEvent mEvent = (MeetingEvent) events.get(i);
										if (mEvent.getContact().getName().equals(contactName)) {
											eventFound = true;
											try {
												remove(mEvent);
											} catch (Exception e) {
												System.out.println(e.getMessage());
											}
										}
									}
								}
								if (!eventFound) {
									System.out.print("Event was not found");
									break;
								}

								System.out.print("Removed the event successfully\n");
								invalidInput = false;
								break;
							case 2:
								// Assuming the description is unique for each event
								System.out.print("Enter the short description about the event: ");
								shortDescription = In.nextLine();

								events = getEventsByDate(d);
								eventFound = false;
								for (int i = 0; i < events.size(); i++) {
									if (events.get(i) instanceof EventWithoutMeeting) { // looks for MeetingEvent
																						// type
																						// events
										EventWithoutMeeting nEvent = (EventWithoutMeeting) events.get(i);
										if (nEvent.getDescription().equals(shortDescription)) {
											eventFound = true;
											try {
												remove(nEvent);
											} catch (Exception e) {
												System.out.println(e.getMessage());
											}
										}
									}
								}
								if (!eventFound) {
									System.out.print("Event was not found");
									break;
								}

								System.out.print("Removed the event successfully");
								invalidInput = false;
								break;
							default:
								System.out.println("Invalid Input. Try Again..");
								break;
						}
					}
					break;
				case 3:
					d = receiveDate(In);
					printEventsByDate(d);
					break;
				case 7:
					System.out.print("Goobye!\n");
					exit();
					exit = true;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
			}
		}
	}

	@Override
	public void printAll() {
		// Enter Code Here
	}

	public static abstract class Event {
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

	public static class MeetingEvent extends Event {
		private Contact contact;

		public MeetingEvent(Date date, int durationMinutes, Contact contact) {
			super(date, durationMinutes);
			this.contact = contact;
		}

		public Contact getContact() {
			return contact;
		}

		public String toString() {
			return "Meeting Event details: Date: " + date.toString() + ", duration: " + durationMinutes + ", Contact: "
					+ getContact();
		}
	}

	public static class EventWithoutMeeting extends Event {
		private String description;

		public EventWithoutMeeting(Date date, int durationMinutes, String description) {
			super(date, durationMinutes);
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public String toString() {
			return "Event details: Date: " + date.toString() + ", duration: " + durationMinutes + ", description:  "
					+ getDescription();
		}
	}

	public static class Date {
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

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Date) {
				Date d = (Date) obj;
				return this.day == d.day && this.month == d.month && this.year == d.year;
			}
			return false;
		}
	}
}