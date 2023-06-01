package BookMyShow.repository;

import BookMyShow.models.Screen;

import java.util.*;
import java.util.stream.Collectors;

public class ScreenRepoImpl implements ScreenRepo {
    static Set<Screen> screensSet = new HashSet<>();

    @Override
    public Screen addScreen(Screen screen) {
        screensSet.add(screen);
        return screen;
    }

    @Override
    public List<Screen> getScreens(String theaterId) {
        return screensSet.stream()
                .filter(screen -> screen.getTheaterId().compareTo(theaterId) == 0)
                .collect(Collectors.toList());
    }

    @Override
    public Screen getScreen(String screenId) {
        return screensSet.stream()
                .filter(screen -> screen.getId().compareTo(screenId) == 0)
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public void removeScreen(String screenId) {
        screensSet.removeIf(screen -> screen.getId().compareTo(screenId) == 0);
    }
}
