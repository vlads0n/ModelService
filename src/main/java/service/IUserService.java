package main.java.service;

import main.java.model.User;

import java.util.List;

/**
 * Created by Влад on 22.08.2017.
 */
public interface IUserService {
    List<User> getAll();
    void updateUser(User user);
    void deleteUser(Long id);
    void addUser(User user);
}
