package splitwise.services;

import splitwise.models.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    public List<User> getUsers();

    public User getUser(String userId);

    public void deleteUser(String userId);

    public void updateUser(User user);
}
