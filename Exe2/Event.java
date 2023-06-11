package Exe2;

import java.util.*;

public class Event {
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
    
}
