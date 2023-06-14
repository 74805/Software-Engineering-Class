package Exe2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

			if (calendar.get(i).isEmpty() || calendar.get(i).get(0).getDate().compareTo(date) >= 0) {
				index = i;
				break;
			}

			Date date2 = calendar.get(i).get(0).getDate();
			if (date2.getYear() == date.getYear() && date2.getMonth() == date.getMonth()
					&& date2.getDay() == date.getDay()) {
				index = i;
				break;
			}
		}
		return index;
	}

	public void printSameContactEvent(String name) {
		for (int i = 0; i < calendar.size(); i++) {
			for (int j = 0; j < calendar.get(i).size(); j++) {
				Event event = calendar.get(i).get(j);
				if (event instanceof MeetingEvent && ((MeetingEvent) event).getContact().getName().equals(name)) {
					System.out.println(event);
				}
			}
		}
	}

	public void deletTheoverlappingEvents(List<List<Event>> calendar) {
		for (int i = 0; i < calendar.size(); i++) {
			for (int j = 0; j < calendar.get(i).size(); j++)
				for (int a = j + 1; a < calendar.get(i).size(); a++) {
					Event event1 = calendar.get(i).get(j);
					Event event2 = calendar.get(i).get(a);
					Date date_event1 = calendar.get(i).get(j).getDate();
					Date date_event2 = calendar.get(i).get(a).getDate();
					if (date_event1.getYear() == date_event2.getYear()
							&& date_event1.getMonth() == date_event2.getMonth()
							&& date_event1.getDay() == date_event2.getDay()) {
						if (date_event1.getHours() == date_event2.getHours()) {
							if (date_event1.getMinutes() == date_event2.getMinutes()) {
								try {
									remove(event2);
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
							if (date_event1.getMinutes() + event1.durationMinutes > date_event2.getMinutes()) {
								try {
									remove(event2);
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
							if (date_event2.getMinutes() + event2.durationMinutes > date_event1.getMinutes()) {
								try {
									remove(event1);
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}
						if (date_event1.getHours() == date_event2.getHours() + 1) {
							if (date_event1.getMinutes() + event1.durationMinutes - 60 > date_event2.getMinutes()) {
								try {
									remove(event2);
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}
						if (date_event1.getHours() + 1 == date_event2.getHours()) {
							if (date_event2.getMinutes() + event2.durationMinutes - 60 > date_event1.getMinutes()) {
								try {
									remove(event1);
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}
					}
				}
		}
	}

	public void printAllEvents() {// print all event
		for (int i = 0; i < calendar.size(); i++) {
			for (int j = 0; j < calendar.get(i).size(); j++) {
				calendar.get(i).get(j).toString();
			}
		}
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

			if (calendar.get(index).size() != 0 && calendar.get(index).get(0).getDate().equals(eve.getDate())) {
				calendar.get(index).add(index, eve);
				return;
			}

			for (int i = calendar.size() - 1; i > index; i--) { // shift the events to the right
				calendar.set(i, calendar.get(i - 1));
			}
			calendar.set(index, new ArrayList<>());
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

			if (calendar.get(dateIndex).isEmpty()) { // if the date is empty, shift the events to the left
				for (int i = dateIndex; i < calendar.size() - 1; i++) {
					calendar.set(i, calendar.get(i + 1));
				}
				calendar.set(calendar.size() - 1, new ArrayList<>());
			}
		} catch (Exception e) {
			throw new Exception("Invalid object type");
		}
	}

	public Date receiveDate(Scanner In) {
		int minute = 0, hour = 0, day = 0, month = 0, year = 0;

		System.out.println("Enter the date: ");
		while (true) {
			System.out.print("Enter the year: ");
			year = In.nextInt();
			In.nextLine();
			if (year < 0) { // checks the validity of the input
				System.out.println("Invalid year. Please enter a positive value.");
				continue;
			}

			System.out.print("Enter the month: ");
			month = In.nextInt();
			In.nextLine();
			if (month < 1 || month > 12) { // checks the validity of the input
				System.out.println("Invalid month. Please enter a value between 1 and 12.");
				continue;
			}

			System.out.print("Enter the day: ");
			day = In.nextInt();
			In.nextLine();
			if (day < 1 || day > 30) { // checks the validity of the input
				System.out.println("Invalid day. Please enter a value between 1 and 31.");
				continue;
			}

			System.out.print("Enter the hour (0-23): ");
			hour = In.nextInt();
			In.nextLine();
			if (hour < 0 || hour > 23) { // checks the validity of the input
				System.out.println("Invalid hour. Please enter a value between 0 and 23.");
				continue;
			}

			System.out.print("Enter the minute (0-59): ");
			minute = In.nextInt();
			In.nextLine();
			if (hour < 0 || hour > 59) { // checks the validity of the input
				System.out.println("Invalid minute. Please enter a value between 0 and 59.");
				continue;
			}

			break;

		}

		// if everything has met the requirements, a new Date object will be made.
		@SuppressWarnings("deprecation")
		Date date = new Date(year - 1900, month - 1, day, hour, minute);
		return date;
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
		List<Event> eventsByDate = getEventsByDate(date); // takes the list from the function g
		if (eventsByDate.isEmpty() || eventsByDate.get(0).getDate().getYear() != date.getYear()
				|| eventsByDate.get(0).getDate().getMonth() != date.getMonth()
				|| eventsByDate.get(0).getDate().getDay() != date.getDay()) { // if the date has no events / meetings
			System.out.println("No events scheduled for the specified date.");
			return;
		}

		for (Event event : eventsByDate) {
			System.out.println(event.toString()); // print the events
		}
	}

	public void removeContacts() {
		for (int i = 0; i < calendar.size(); i++) {
			for (int j = 0; j < calendar.get(i).size(); j++) {
				if (calendar.get(i).get(j) instanceof MeetingEvent && phoneBook
						.searchContact(((MeetingEvent) calendar.get(i).get(j)).getContact().getName()) == null) {
					try {
						remove(calendar.get(i).get(j));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void run() {
		System.out.println("\nWelcome to the Calendar App!");

		removeContacts();

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
			Contact c;
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

								c = phoneBook.searchContact(contactName);
								if (c == null) {
									System.out.print("There's no contact with the name " + contactName
											+ ", you can add it in the Contacts App");

									break;
								}

								d = receiveDate(In);
								eve = receiveEventWithContact(d, c, In);
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
					int day = 0, month = 0, year = 0;

					System.out.println("Enter the date: ");
					while (true) {
						System.out.print("Enter the year: ");
						year = In.nextInt();
						In.nextLine();
						if (year < 0) { // checks the validity of the input
							System.out.println("Invalid year. Please enter a positive value.");
							continue;
						}

						System.out.print("Enter the month: ");
						month = In.nextInt();
						In.nextLine();
						if (month < 1 || month > 12) { // checks the validity of the input
							System.out.println("Invalid month. Please enter a value between 1 and 12.");
							continue;
						}

						System.out.print("Enter the day: ");
						day = In.nextInt();
						In.nextLine();
						if (day < 1 || day > 30) { // checks the validity of the input
							System.out.println("Invalid day. Please enter a value between 1 and 31.");
							continue;
						}

						break;
					}

					d = new Date(year - 1900, month - 1, day);
					printEventsByDate(d);
					break;
				case 4:
					System.out.println("Please enter contact name: ");
					In.nextLine();
					String name = In.nextLine();

					// validate that the name contains only letters
					if (!name.matches("[a-zA-Z]+")) {
						System.out.print("Invalid name. Try again.");
						break;
					}

					printSameContactEvent(name);
					break;
				case 5:
					deletTheoverlappingEvents(calendar);
					System.out.println("delet The overlapping Events: ");
					break;
				case 6:
					System.out.println("All evens: ");
					printAllEvents();
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
		System.out.println("All events: ");
		for (int i = 0; i < calendar.size(); i++) {
			for (int j = 0; j < calendar.get(i).size(); j++) {
				System.out.println(calendar.get(i).get(j));
			}
		}
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

}
