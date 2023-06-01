package BookMyShow.repository;

import BookMyShow.models.Screen;

import java.util.List;

public interface ScreenRepo {
    public Screen addScreen(Screen screen);
    public List<Screen> getScreens(final String theaterId);
    public Screen getScreen(final String screenId);
    public void removeScreen(final String screenId);
}
