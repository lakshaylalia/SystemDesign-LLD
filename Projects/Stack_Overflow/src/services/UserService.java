package services;

import exceptions.BusinessRuleViolationException;
import exceptions.InvalidInputException;
import exceptions.UserNotFoundException;
import models.User;
import dao.UserDAO;
import dao.UserDAOImpl;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public User addUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new InvalidInputException("Username cannot be empty");
        }
        if (userDAO.getUserByUsername(user.getUsername()) != null) {
            throw new BusinessRuleViolationException("Username already taken");
        }
        return userDAO.save(user);
    }

    public User getUserById(Long id) {
        User user = userDAO.getById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            throw new InvalidInputException("No user found with username: " + username);
        }
        return user;
    }

    public User update(User user) {
        return userDAO.update(user);
    }

    public void delete(Long id) {
        userDAO.delete(id);
    }
}