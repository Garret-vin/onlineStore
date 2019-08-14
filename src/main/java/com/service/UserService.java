package com.service;

import com.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void add(User user);

    void update(User user);

    void remove(Long id);

    Optional<User> getById(Long id);

    Optional<User> getByLogin(String login);

    Optional<User> getByLoginOrEmail(String login, String email);

    List<User> getAll();
}
