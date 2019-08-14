package com.dao;

import com.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void add(Product product);

    void update(Product product);

    void remove(Long id);

    List<Product> getAll();

    Optional<Product> getById(Long id);
}
