package calendar.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class Event {

    private String eventId;

    private String name;

    private User creator;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Set<User> userSet;

    private Map<User, Boolean> userMap;


    public Event (String name, User creator, LocalDateTime startTime, LocalDateTime endTime, Map<User, Boolean>userMap) {
        this.name = name;
        this.creator = creator;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userMap = userMap;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Map<User, Boolean> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<User, Boolean> userMap) {
        this.userMap = userMap;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
