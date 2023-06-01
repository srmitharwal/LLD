package BookMyShow.repository;

import BookMyShow.models.Theater;

import java.util.*;
import java.util.stream.Collectors;

public class TheaterRepoImpl implements TheaterRepo {
    static Set<Theater> theaters = new HashSet<>();

    @Override
    public Theater addTheater(Theater theater) {
        theaters.add(theater);
        return theater;
    }

    @Override
    public List<Theater> listTheaters(String cityName) {
        return theaters.stream()
                .filter(theater -> theater.getCityName().compareTo(cityName) == 0)
                .collect(Collectors.toList());

    }

    @Override
    public Theater getTheater(String theaterId) {
        return theaters.stream()
                .filter(theater -> theater.getId().compareTo(theaterId) == 0)
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public void remove(String theaterId) throws Exception {
        Theater theater = theaters.stream()
                .filter(var -> var.getId().compareTo(theaterId) == 0)
                .collect(Collectors.toList()).get(0);
        if (theater == null) {
            throw new Exception("Theater with given id doesnt exist");

        }

        theaters.remove(theater);
    }

}
