package com.springsecurity.service;

import com.springsecurity.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(int id);

    void saveProduct(Product product);

}
