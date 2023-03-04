package com.example.teste.cadastro_funcionarios_clientes.service;

import com.example.teste.cadastro_funcionarios_clientes.exception.NotFoundException;
import com.example.teste.cadastro_funcionarios_clientes.model.Customer;
import com.example.teste.cadastro_funcionarios_clientes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new NotFoundException("Customer not found");
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        } else {
            throw new NotFoundException("Customer not found");
        }
    }

    public Customer update(Long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setDocument(customer.getDocument());
            existingCustomer.setPostalCode(customer.getPostalCode());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setAddressNumber(customer.getAddressNumber());
            existingCustomer.setCity(customer.getCity());
            existingCustomer.setState(customer.getState());
            existingCustomer.setCountry(customer.getCountry());
            return customerRepository.save(existingCustomer);
        } else {
            throw new NotFoundException("Customer not found");
        }
    }
}
