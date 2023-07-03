package calendar.services;


import calendar.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    static Map<String, User> userMap;

    public UserServiceImpl() {
        if (userMap == null) {
            userMap = new HashMap<>();
        }
    }
    public User createUser(User user) {
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        user.setUserId(userId);
        userMap.put(userId, user);
        return user;

    }
}
