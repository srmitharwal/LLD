package BookMyShow.repository;

import BookMyShow.models.Show;

import java.util.HashSet;
import java.util.Set;

public class ShowRepoImpl implements ShowRepo {
    static Set<Show> showSet = new HashSet<>();

    @Override
    public Show addShow(Show show) {
        showSet.add(show);
        return show;
    }

}
