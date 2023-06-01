package BookMyShow.models;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private String id;
    private String name;
    private String theaterId;
    private List<Show> shows;
    private List<Seat> seats;

    //metadata of screen

    public Screen(String name, String theaterId) {
        this.name = name;
        this.theaterId = theaterId;
        this.shows = new ArrayList<>();
        this.seats = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
