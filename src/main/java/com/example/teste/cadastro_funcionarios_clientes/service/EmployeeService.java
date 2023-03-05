package com.example.teste.cadastro_funcionarios_clientes.service;

import com.example.teste.cadastro_funcionarios_clientes.exception.NotFoundException;
import com.example.teste.cadastro_funcionarios_clientes.model.Employee;
import com.example.teste.cadastro_funcionarios_clientes.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Integer defaultPageSize = 20;

    public Page<Employee> getAllEmployees(Optional<Integer> page, Integer pageSize) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, pageSize);
        return employeeRepository.findAll(pageable);
    }
    
    public Page<Employee> getAllEmployees(Optional<Integer> page) {
        return getAllEmployees(page, defaultPageSize);
    }

    public Page<Employee> getAllEmployeesByName(String name, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return employeeRepository.findAllByName(name, pageable);
    }
    
    public Page<Employee> getAllEmployeesByDocument(String document, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return employeeRepository.findAllByDocument(document, pageable);
    }
    
    public Page<Employee> getAllEmployeesByPostalCode (String postalCode, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return employeeRepository.findAllByPostalCode(postalCode, pageable);
    }
    
    public Page<Employee> getAllEmployeesByAddress (String address, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return employeeRepository.findAllByAddress(address, pageable);
    }
    
    public Page<Employee> getAllEmployeesByAddressNumber (String addressNumber, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return employeeRepository.findAllByAddressNumber(addressNumber, pageable);
    }
    
    public Page<Employee> getAllEmployeesByCity (String city, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return employeeRepository.findAllByCity(city, pageable);
    }
    
    public Page<Employee> getAllEmployeesByState (String state, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return employeeRepository.findAllByState(state, pageable);
    }
    
    public Page<Employee> getAllEmployeesByCountry (String country, Optional<Integer> page) {
        if(page.isEmpty()) page = Optional.of(0);
        Integer startPage = Integer.valueOf(page.get());
        Pageable pageable = PageRequest.of(startPage, defaultPageSize);
        return employeeRepository.findAllByCountry(country, pageable);
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new NotFoundException("Employee not found");
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Boolean deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return true;
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
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
        } else {
            throw new NotFoundException("Employee not found");
        }
    }
}
