package com.springsecurity.service;

import com.springsecurity.entity.Product;
import com.springsecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with id = [" + id + "] not found"));
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

}
