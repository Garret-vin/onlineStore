package com.dao;

import com.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void add(Product product);

    void remove(Product product);

    void update(Long productId, Product product);

    List<Product> getAll();

    Optional<Product> getById(Long id);
}
