package BookMyShow.services;

import BookMyShow.models.Show;
import BookMyShow.repository.ShowRepo;

import java.util.List;

public class ShowServiceImpl implements ShowService {

    private ShowRepo showRepo;

    public ShowServiceImpl(ShowRepo showRepo) {
        this.showRepo = showRepo;
    }

    @Override
    public void addShow(Show show) {
        //String screenId = show.getScreenId();
        showRepo.addShow(show);
    }

    @Override
    public List<Show> listShows(String cityName, String movieName) {
        return null;
    }

    @Override
    public Show getShow(String showId) {
        return null;
    }

    @Override
    public void deleteShow(String showId) {

    }
}
