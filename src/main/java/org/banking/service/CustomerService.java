package org.banking.service;

import org.banking.Model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer saveCustomer(Customer customer); // Create or update

    List<Customer> getAllCustomers(); // Read All

    Optional<Customer> getCustomerById(String id); // Read One

    void deleteCustomer(String id); // Delete
}
