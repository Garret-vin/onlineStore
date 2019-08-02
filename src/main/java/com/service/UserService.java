package com.service;

import com.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void add(User user);

    List<User> getAll();

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

    void update(Long userId, User user);

    void remove(User user);

    Optional<User> getByLoginOrEmail(String login, String email);
}
