package com.example.teste.cadastro_funcionarios_clientes.util;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.teste.cadastro_funcionarios_clientes.model.Employee;
import com.example.teste.cadastro_funcionarios_clientes.service.EmployeeService;

@Component
public class EmployeeExcelExporter {
    @Autowired
    private EmployeeService employeeService;
    
    public void exportAllEmployeesToExcel(HttpServletResponse response, Optional<Integer> page) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees_"+String.valueOf(System.currentTimeMillis())+".xlsx";
        response.setHeader(headerKey, headerValue);
        
        Page<Employee> employeesPage = employeeService.getAllEmployees(page, 100);
        List<Employee> employees = employeesPage.getContent();
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");
        
        Row headerRow = sheet.createRow(0);
        Cell idCell = headerRow.createCell(0);
        idCell.setCellValue("ID");
        Cell nameCell = headerRow.createCell(1);
        nameCell.setCellValue("Name");
        Cell documentCell = headerRow.createCell(2);
        documentCell.setCellValue("Document");
        Cell postalCodeCell = headerRow.createCell(3);
        postalCodeCell.setCellValue("Postal Code");
        Cell addressCell = headerRow.createCell(4);
        addressCell.setCellValue("Address");
        Cell addressNumberCell = headerRow.createCell(5);
        addressNumberCell.setCellValue("Address Number");
        Cell cityCell = headerRow.createCell(6);
        cityCell.setCellValue("City");
        Cell stateCell = headerRow.createCell(7);
        stateCell.setCellValue("State");
        Cell countryCell = headerRow.createCell(8);
        countryCell.setCellValue("Country");
        
        int rowNum = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getDocument());
            row.createCell(3).setCellValue(employee.getPostalCode());
            row.createCell(4).setCellValue(employee.getAddress());
            row.createCell(5).setCellValue(employee.getAddressNumber());
            row.createCell(6).setCellValue(employee.getCity());
            row.createCell(7).setCellValue(employee.getState());
            row.createCell(8).setCellValue(employee.getCountry());
        }
        
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
