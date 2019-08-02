package com.dao;

import com.model.Order;
import com.model.User;

import java.util.Optional;

public interface OrderDao {

    void add(Order order);

    Optional<Order> getById(Long id);

    Optional<Order> getLastOrderForUser(User user);
}
