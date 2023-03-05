package com.example.teste.cadastro_funcionarios_clientes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.cadastro_funcionarios_clientes.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAllByName(String name, Pageable pageable);

    Page<Employee> findAllByDocument(String document, Pageable pageable);

    Page<Employee> findAllByPostalCode (String postalCode, Pageable pageable);

    Page<Employee> findAllByAddress (String address, Pageable pageable);

    Page<Employee> findAllByAddressNumber (String addressNumber, Pageable pageable);

    Page<Employee> findAllByCity (String city, Pageable pageable);

    Page<Employee> findAllByState (String state, Pageable pageable);

    Page<Employee> findAllByCountry (String country, Pageable pageable);
}
