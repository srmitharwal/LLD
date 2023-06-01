package BookMyShow.repository;

import BookMyShow.models.Theater;

import java.util.List;

public interface TheaterRepo {

    public Theater addTheater(Theater theater);
    public List<Theater> listTheaters( final String cityName);
    public Theater getTheater(final String theaterId);
    public void remove(final String theaterID) throws Exception;
}
