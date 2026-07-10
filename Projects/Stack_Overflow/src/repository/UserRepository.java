package repository;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private static final UserRepository instance = new UserRepository();

    private Map<Long, User> idToUser = new ConcurrentHashMap<>();
    private Map<String, User> usernameToUser = new ConcurrentHashMap<>();

    private UserRepository() {}

    public static UserRepository getInstance() {
        return instance;
    }

    public User save(User user) {
        idToUser.put(user.getId(), user);
        usernameToUser.put(user.getUsername(), user);
        return user;
    }

    public User getUserById(Long id) {
        return  idToUser.get(id);
    }

    public User getUserByUsername(String username) {
        return usernameToUser.get(username);
    }

    public List<User> getAll() {
        return new ArrayList<>(idToUser.values());
    }

    public User update(User user) {
        User existing = idToUser.get(user.getId());
        if (existing != null) {
            usernameToUser.remove(existing.getUsername());
        }
        idToUser.put(user.getId(), user);
        usernameToUser.put(user.getUsername(), user);
        return user;
    }

    public void delete(Long id) {
        User user = idToUser.get(id);
        if (user != null) {
            usernameToUser.remove(user.getUsername());
        }
        idToUser.remove(id);
    }
}
