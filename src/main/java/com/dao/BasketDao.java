package com.dao;

import com.model.Basket;
import com.model.Product;
import com.model.User;

import java.util.Optional;

public interface BasketDao {

    void add(Basket basket);

    void addProduct(Basket basket, Product product);

    int size(Basket basket);

    Optional<Basket> getBasketByUser(User user);
}
