package BookMyShow.services;

import BookMyShow.models.Theater;

import java.util.List;

public interface TheaterService {
    public Theater addTheater(Theater theater);
    public List<Theater> listTheaters(String cityName);
    public Theater getTheater(String theaterId);
    public void removeTheater(String theaterId) throws Exception;
    public Theater updateTheater(String theaterId, Theater theater) throws Exception;

}
