package BookMyShow.services;

import BookMyShow.models.Show;

import java.util.List;

public interface ShowService {
    public void addShow(Show show);
    public List<Show> listShows(String cityName, String movieName);
    public Show getShow(String showId);
    public void deleteShow(String showId);
}
