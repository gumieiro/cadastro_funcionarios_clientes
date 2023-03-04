package com.example.teste.cadastro_funcionarios_clientes.service;

import com.example.teste.cadastro_funcionarios_clientes.repository.EmployeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teste.cadastro_funcionarios_clientes.exception.NotFoundException;
import com.example.teste.cadastro_funcionarios_clientes.model.Employee;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) throws NotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) throws NotFoundException {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setDocument(employee.getDocument());
        existingEmployee.setBirthdate(employee.getBirthdate());
        existingEmployee.setPostalCode(employee.getPostalCode());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setAddressNumber(employee.getAddressNumber());
        existingEmployee.setCity(employee.getCity());
        existingEmployee.setState(employee.getState());
        existingEmployee.setCountry(employee.getCountry());

        return employeeRepository.save(existingEmployee);
    }

    public Boolean deleteEmployee(Long id) throws NotFoundException {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        employeeRepository.delete(existingEmployee);
        return true;
    }
}
