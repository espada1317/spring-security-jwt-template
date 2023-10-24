package com.springsecurity.controller;

import com.springsecurity.entity.Customer;
import com.springsecurity.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Customer getProductById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>("Customer added successfully", HttpStatus.CREATED);
    }

}