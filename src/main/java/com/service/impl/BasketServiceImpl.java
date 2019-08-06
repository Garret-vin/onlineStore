package com.service.impl;

import com.dao.BasketDao;
import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private BasketDao basketDao;

    @Autowired
    public BasketServiceImpl(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    @Override
    @Transactional
    public void add(Basket basket) {
        basketDao.add(basket);
    }

    @Override
    @Transactional
    public void addProduct(Basket basket, Product product) {
        basketDao.addProduct(basket, product);
    }

    @Override
    public int size(Basket basket) {
        return basket.getProductList().size();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Basket> getBasketByUser(User user) {
        return basketDao.getBasketByUser(user);
    }
}
