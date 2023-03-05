package com.example.teste.cadastro_funcionarios_clientes.service;

import com.example.teste.cadastro_funcionarios_clientes.exception.NotFoundException;
import com.example.teste.cadastro_funcionarios_clientes.model.Customer;
import com.example.teste.cadastro_funcionarios_clientes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private Integer defaultPageSize = 20;

    public Page<Customer> getAllCustomers(Optional<Integer> page, Integer pageSize) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, pageSize);
        return customerRepository.findAll(pageable);
    }

    public Page<Customer> getAllCustomers(Optional<Integer> page) {
        return getAllCustomers(page, defaultPageSize);
    }

    public Page<Customer> getAllCustomersByName(String name, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return customerRepository.findAllByName(name, pageable);
    }
    
    public Page<Customer> getAllCustomersByDocument(String document, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return customerRepository.findAllByDocument(document, pageable);
    }
    
    public Page<Customer> getAllCustomersByPostalCode (String postalCode, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return customerRepository.findAllByPostalCode(postalCode, pageable);
    }
    
    public Page<Customer> getAllCustomersByAddress (String address, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return customerRepository.findAllByAddress(address, pageable);
    }
    
    public Page<Customer> getAllCustomersByAddressNumber (String addressNumber, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return customerRepository.findAllByAddressNumber(addressNumber, pageable);
    }
    
    public Page<Customer> getAllCustomersByCity (String city, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return customerRepository.findAllByCity(city, pageable);
    }
    
    public Page<Customer> getAllCustomersByState (String state, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return customerRepository.findAllByState(state, pageable);
    }
    
    public Page<Customer> getAllCustomersByCountry (String country, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return customerRepository.findAllByCountry(country, pageable);
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new NotFoundException("Customer not found");
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Boolean deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return true;
        } else {
            throw new NotFoundException("Customer not found");
        }
    }

    public Customer updateCustomer(Long id, Customer customer) {
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
