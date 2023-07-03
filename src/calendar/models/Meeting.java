package calendar.models;

import java.time.LocalDateTime;
import java.util.Map;

public class Meeting extends Event {

    private String agenda;
    public Meeting(String name, User creator, LocalDateTime startTime,
                   LocalDateTime endTime, Map<User, Boolean> userMap) {
        super(name, creator, startTime, endTime, userMap);
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}
