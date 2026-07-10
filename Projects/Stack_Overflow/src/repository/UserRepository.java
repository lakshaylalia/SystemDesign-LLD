package repository;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private static final UserRepository instance = new UserRepository();

    private Map<Long, User> idToUser = new ConcurrentHashMap<>();

    private UserRepository() {}

    public static UserRepository getInstance() {
        return instance;
    }

    public User save(User user) {
        idToUser.put(user.getId(), user);
        return user;
    }

    public User getUserById(Long id) {
        return  idToUser.get(id);
    }

    public User getUserByUsername(String username) {
        for (User user : idToUser.values()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAll() {
        return new ArrayList<>(idToUser.values());
    }

    public User update(User user) {
        idToUser.put(user.getId(), user);
        return user;
    }

    public void delete(Long id) {
        idToUser.remove(id);
    }
}
