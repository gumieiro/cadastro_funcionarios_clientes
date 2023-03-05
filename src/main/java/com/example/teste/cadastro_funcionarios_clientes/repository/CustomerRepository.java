package com.example.teste.cadastro_funcionarios_clientes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.cadastro_funcionarios_clientes.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAllByName(String name, Pageable pageable);

    Page<Customer> findAllByDocument(String document, Pageable pageable);

    Page<Customer> findAllByPostalCode (String postalCode, Pageable pageable);

    Page<Customer> findAllByAddress (String address, Pageable pageable);

    Page<Customer> findAllByAddressNumber (String addressNumber, Pageable pageable);

    Page<Customer> findAllByCity (String city, Pageable pageable);

    Page<Customer> findAllByState (String state, Pageable pageable);

    Page<Customer> findAllByCountry (String country, Pageable pageable);
}
