package com.portal.audit.service;

import com.portal.audit.aspect.LogEvent;
import com.portal.audit.entity.Customer;
import com.portal.audit.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @LogEvent(action = "ADD CUSTOMER")
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @LogEvent(action = "UPDATE CUSTOMER")
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @LogEvent(action = "DELETE CUSTOMER")
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


}
