package com.example.teste.cadastro_funcionarios_clientes.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.teste.cadastro_funcionarios_clientes.model.Employee;
import com.example.teste.cadastro_funcionarios_clientes.service.EmployeeService;
import com.example.teste.cadastro_funcionarios_clientes.util.EmployeeExcelExporter;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private EmployeeExcelExporter employeeExcelExporter;

    @GetMapping("/export")
    public void exportEmployeesToExcel(HttpServletResponse response, @RequestParam(name = "page", required = false) Optional<Integer> page) throws IOException {
        employeeExcelExporter.exportAllEmployeesToExcel(response, page);
    }

    @GetMapping
    public Page<Employee> getAllEmployees(@RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployees(page);
    }
    
    @GetMapping("/searchBy/name/{name}")
    public Page<Employee> findAllEmployeesByName (@PathVariable String name, @RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployeesByName(name, page);
    }

    @GetMapping("/searchBy/document/{document}")
    public Page<Employee> findAllEmployeesByDocument (@PathVariable String document, @RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployeesByDocument(document, page);
    }

    @GetMapping("/searchBy/postalCode/{postalCode}")
    public Page<Employee> findAllEmployeesByPostalCode (@PathVariable String postalCode, @RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployeesByPostalCode(postalCode, page);
    }

    @GetMapping("/searchBy/address/{address}")
    public Page<Employee> findAllEmployeesByAddress (@PathVariable String address, @RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployeesByAddress(address, page);
    }

    @GetMapping("/searchBy/addressNumber/{addressNumber}")
    public Page<Employee> findAllEmployeesByAddressNumber (@PathVariable String addressNumber, @RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployeesByAddressNumber(addressNumber, page);
    }

    @GetMapping("/searchBy/city/{city}")
    public Page<Employee> findAllEmployeesByCity (@PathVariable String city, @RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployeesByCity(city, page);
    }

    @GetMapping("/searchBy/state/{state}")
    public Page<Employee> findAllEmployeesByState (@PathVariable String state, @RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployeesByState(state, page);
    }

    @GetMapping("/searchBy/country/{country}")
    public Page<Employee> findAllEmployeesByCountry (@PathVariable String country, @RequestParam("page") Optional<Integer> page) {
        return employeeService.getAllEmployeesByCountry(country, page);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee Employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, Employee);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean success = employeeService.deleteEmployee(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

