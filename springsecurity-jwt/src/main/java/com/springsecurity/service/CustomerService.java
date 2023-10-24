package com.springsecurity.service;

import com.springsecurity.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void saveCustomer(Customer customer);

}
