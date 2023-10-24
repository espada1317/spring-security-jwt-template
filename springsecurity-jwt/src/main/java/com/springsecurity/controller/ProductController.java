package com.springsecurity.controller;

import com.springsecurity.entity.Product;
import com.springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Product> getAllTheProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

}