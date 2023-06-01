package BookMyShow.services;

import BookMyShow.models.Theater;
import BookMyShow.repository.TheaterRepo;

import java.util.List;
import java.util.UUID;

public class TheaterServiceImpl implements TheaterService {

    private TheaterRepo theaterRepo;
    public TheaterServiceImpl(TheaterRepo theaterRepo) {
        this.theaterRepo = theaterRepo;
    }

    @Override
    public Theater addTheater(Theater theater) {
        String id = UUID.randomUUID().toString();
        theater.setId(id);
        return theaterRepo.addTheater(theater);
    }

    @Override
    public List<Theater> listTheaters(String cityName) {
        return theaterRepo.listTheaters(cityName);
    }

    @Override
    public Theater getTheater(String theaterId) {
        return theaterRepo.getTheater(theaterId);
    }

    @Override
    public void removeTheater(String theaterId) throws Exception {
        theaterRepo.remove(theaterId);
    }

    @Override
    public Theater updateTheater(String theaterId, Theater theater) throws Exception {
        Theater savedTheater = theaterRepo.getTheater(theaterId);

        if (savedTheater == null) {
            throw new Exception("threatId doesnt exist");
        }

        return theaterRepo.addTheater(theater);
    }
}
