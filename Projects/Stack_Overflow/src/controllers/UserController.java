package controllers;

import dto.UserDTO;
import mapper.DTOMapper;
import models.User;
import services.UserService;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public UserDTO addUser(User user) {
        User saved = userService.addUser(user);
        return DTOMapper.toUserDTO(saved);
    }

    public UserDTO getUserById(Long id) {
        User user = userService.getUserById(id);
        return DTOMapper.toUserDTO(user);
    }

    public UserDTO getUserByUsername(String username) {
        User user = userService.getUserByUsername(username);
        return DTOMapper.toUserDTO(user);
    }

    public UserDTO update(User user) {
        User updated = userService.update(user);
        return DTOMapper.toUserDTO(updated);
    }

    public void delete(Long id) {
        userService.delete(id);
    }
}