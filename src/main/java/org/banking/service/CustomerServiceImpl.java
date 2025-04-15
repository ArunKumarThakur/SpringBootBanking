package org.banking.service;

import org.banking.Model.Customer;
import org.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteCustomer(String id) {
        repository.deleteById(id);
    }
}
