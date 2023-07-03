package calendar.services;

import calendar.models.Event;
import calendar.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    public Event createEvent(Event event);

    public List<Event> getEvents(User user, LocalDateTime startTime, LocalDateTime endTime);

    public Event getEvent(String eventId);

    public Event updateEvent(Event event);

    public void deleteEvent(String eventId);
}
