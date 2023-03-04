package com.example.teste.cadastro_funcionarios_clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.cadastro_funcionarios_clientes.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
