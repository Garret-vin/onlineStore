package com.service.impl;

import com.dao.OrderDao;
import com.model.Order;
import com.model.User;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public void add(Order order) {
        orderDao.add(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getLastOrderForUser(User user) {
        return orderDao.getLastOrderForUser(user);
    }
}
