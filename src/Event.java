//package event management system;

import java.time.LocalDate;

public class Event {
    private int eventId;
    private String name;
    private LocalDate date;
    private String location;
    private int capacity;

    public Event() {}

    public Event(int eventId, String name, LocalDate date, String location) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public Event(int eventId, String name, LocalDate date, String location,int capacity) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.capacity=capacity;
    }
    
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
