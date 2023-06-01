package BookMyShow.services;

import BookMyShow.models.Screen;
import BookMyShow.models.Theater;
import BookMyShow.repository.ScreenRepo;

import java.util.List;
import java.util.UUID;

public class ScreenServiceImpl implements ScreenService {

    private ScreenRepo screenRepo;
    private TheaterService theaterService;

    public ScreenServiceImpl(ScreenRepo screenRepo, TheaterService theaterService) {
        this.screenRepo = screenRepo;
        this.theaterService = theaterService;

    }

    @Override
    public Screen addScreen(Screen screen) throws Exception {
        Theater theater = theaterService.getTheater(screen.getTheaterId());
        if (theater == null) {
            throw new Exception("Theater doesnt exist");
        }
        String screenId = UUID.randomUUID().toString();
        screen.setId(screenId);
        Screen savedScreed = screenRepo.addScreen(screen);
        theater.getscreenList().add(screen);
        theaterService.updateTheater(theater.getId(), theater);
        return savedScreed;
    }

    @Override
    public List<Screen> listScreens(String theaterId) {
        return screenRepo.getScreens(theaterId);
    }

    @Override
    public Screen getScreen(String screenId) {
        return screenRepo.getScreen(screenId);
    }

    @Override
    public void removeScreen(String screenId) throws Exception {
        Screen screen = screenRepo.getScreen(screenId);

        if (screen == null) {
            throw new Exception("invalid screen id");
        }

        Theater theater  = theaterService.getTheater(screen.getTheaterId());
        theater.getscreenList().removeIf(element -> element.getId().compareTo(screenId) == 0);

        theaterService.updateTheater(theater.getId(), theater);
        screenRepo.removeScreen(screenId);
    }
}
