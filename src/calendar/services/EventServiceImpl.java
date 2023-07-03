package calendar.services;

import calendar.models.Event;
import calendar.models.User;

import java.time.LocalDateTime;
import java.util.*;

public class EventServiceImpl implements  EventService {

    static Map<String, Event> eventMap;

    public EventServiceImpl () {

        if(eventMap == null) {
            eventMap = new HashMap<>();
        }
    }

    @Override
    public Event createEvent(Event event) {
        UUID uuid = UUID.randomUUID();
        String eventId = uuid.toString();

        event.setEventId(eventId);
        eventMap.put(eventId, event);
        return event;
    }

    @Override
    public List<Event> getEvents(User user, LocalDateTime startTime, LocalDateTime endTime) {

        List<Event> allEvents = eventMap.values().stream().toList();

        List<Event> eventList = new ArrayList<>();

        for(Event event : allEvents) {
            if(event.getUserMap().containsKey(user)
                    && event.getStartTime().compareTo(startTime) >= 0 && event.getStartTime().compareTo(endTime) <= 0) {
                eventList.add(event);
            }
        }

        return eventList;

    }

    @Override
    public Event getEvent(String eventId) {
        return eventMap.get(eventId);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventMap.put(event.getEventId(), event);
    }

    @Override
    public void deleteEvent(String eventId) {
        eventMap.remove(eventId);
    }
}
