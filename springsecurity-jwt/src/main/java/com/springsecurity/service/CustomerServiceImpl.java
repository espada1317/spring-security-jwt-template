package com.springsecurity.service;

import com.springsecurity.entity.Customer;
import com.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id = [" + id + "] not found"));
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
