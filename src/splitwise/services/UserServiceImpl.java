package splitwise.services;

import splitwise.Utils.Util;
import splitwise.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    static Map<String, User> userMap = null;

    public UserServiceImpl() {
        if(userMap == null) {
            synchronized (UserServiceImpl.class) {
                if(userMap == null ){
                    userMap = new HashMap<>();
                }
            }
        }
    }

    @Override
    public User createUser(User user) {
        String userId = Util.uuidGenerator();
        user.setUserId(userId);

        userMap.put(userId, user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(String userId) {
        return userMap.get(userId);
    }

    @Override
    public void deleteUser(String userId) {
        userMap.remove(userId);
    }

    @Override
    public void updateUser(User user) {

    }


}
