package com.dao;

import com.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void add(User user);

    void remove(User user);

    void update(Long userId, User user);

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

    Optional<User> getByLoginOrEmail(String login, String email);

    List<User> getAll();
}
