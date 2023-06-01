package BookMyShow.services;

import BookMyShow.models.Screen;

import java.util.List;

public interface ScreenService {
    public Screen addScreen(Screen screen) throws Exception;
    public List<Screen> listScreens(final String theaterId);
    public Screen getScreen(final String screenId);
    public void removeScreen(final String screenId) throws Exception;
}
