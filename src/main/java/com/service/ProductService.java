package com.service;

import com.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void add(Product product);

    void update(Product product);

    void remove(Long id);

    Optional<Product> getById(Long id);

    List<Product> getAll();
}
