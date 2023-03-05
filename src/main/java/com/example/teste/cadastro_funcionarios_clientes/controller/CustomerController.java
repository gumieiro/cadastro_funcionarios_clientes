package com.example.teste.cadastro_funcionarios_clientes.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.teste.cadastro_funcionarios_clientes.model.Customer;
import com.example.teste.cadastro_funcionarios_clientes.service.CustomerService;
import com.example.teste.cadastro_funcionarios_clientes.util.CustomerExcelExporter;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerExcelExporter customerExcelExporter;

    @GetMapping("/export")
    public void exportCustomersToExcel(HttpServletResponse response, @RequestParam(name = "page", required = false) Optional<Integer> page) throws IOException {
        customerExcelExporter.exportAllCustomersToExcel(response, page);
    }


    @GetMapping
    public Page<Customer> getAllCustomers(@RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomers(page);
    }

    @GetMapping("/searchBy/name/{name}")
    public Page<Customer> findAllCustomersByName(String name, @RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomersByName(name, page);
    }

    @GetMapping("/searchBy/document/{document}")
    public Page<Customer> findAllCustomersByDocument(String document, @RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomersByDocument(document, page);
    }

    @GetMapping("/searchBy/postalCode/{postalCode}")
    public Page<Customer> findAllCustomersByPostalCode (String postalCode, @RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomersByPostalCode(postalCode, page);
    }

    @GetMapping("/searchBy/address/{address}")
    public Page<Customer> findAllCustomersByAddress (String address, @RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomersByAddress(address, page);
    }

    @GetMapping("/searchBy/addressNumber/{addressNumber}")
    public Page<Customer> findAllCustomersByAddressNumber (String addressNumber, @RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomersByAddressNumber(addressNumber, page);
    }

    @GetMapping("/searchBy/city/{city}")
    public Page<Customer> findAllCustomersByCity (String city, @RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomersByCity(city, page);
    }

    @GetMapping("/searchBy/state/{state}")
    public Page<Customer> findAllCustomersByState (String state, @RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomersByState(state, page);
    }

    @GetMapping("/searchBy/country/{country}")
    public Page<Customer> findAllCustomersByCountry (String country, @RequestParam(name = "page", required = false) Optional<Integer> page) {
        return customerService.getAllCustomersByCountry(country, page);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer Customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, Customer);
        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        boolean success = customerService.deleteCustomer(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

