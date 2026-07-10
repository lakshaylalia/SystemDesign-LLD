package dao;

import models.User;

import java.util.List;

public interface UserDAO {
    User save(User user);
    User getById(Long id);
    User getUserByUsername(String username);
    List<User> getAll();
    User update(User user);
    void delete(Long id);
}