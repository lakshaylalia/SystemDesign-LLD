package dao;

import models.User;
import repository.UserRepository;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    public UserDAOImpl() {
        this.userRepository = UserRepository.getInstance();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}