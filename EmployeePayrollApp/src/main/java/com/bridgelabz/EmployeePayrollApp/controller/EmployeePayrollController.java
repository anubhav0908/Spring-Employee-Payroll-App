package com.bridgelabz.EmployeePayrollApp.controller;

import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelabz.EmployeePayrollApp.model.Employee;
import com.bridgelabz.EmployeePayrollApp.service.IEmployeePayrollService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j  // Lombok annotation for logging
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeeService;

    @GetMapping("/")
    public String home() {
        log.info("Home endpoint accessed");
        return "Welcome to Employee Payroll Service!";
    }

    // Create Employee
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@Valid  @RequestBody  EmployeeDTO employeeDTO) {
        log.info("Creating Employee: {}", employeeDTO);
        Employee employee = employeeService.createEmployee(employeeDTO);
        log.info("Employee Created: {}", employee);
        return ResponseEntity.ok(employee);
    }

    // Get All Employees
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Fetching all employees");
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Get Employee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        log.info("Fetching Employee with ID: {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);

        return employee
                .<ResponseEntity<?>>map(ResponseEntity::ok)  // Explicitly set generic type
                .orElseGet(() -> {
                    log.warn("Employee not found with ID: {}", id);
                    return ResponseEntity.status(404).body("Employee not found");
                });
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        log.info("Updating Employee with ID: {}", id);
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, employeeDTO);

        return updatedEmployee
                .<ResponseEntity<?>>map(ResponseEntity::ok)  // Explicitly set generic type
                .orElseGet(() -> {
                    log.warn("Employee not found with ID: {}", id);
                    return ResponseEntity.status(404).body("Employee not found");
                });
    }


    // Delete Employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        log.info("Deleting Employee with ID: {}", id);
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            log.info("Employee Deleted Successfully with ID: {}", id);
            return ResponseEntity.ok("Employee deleted with ID: " + id);
        } else {
            log.warn("Employee not found with ID: {}", id);
            return ResponseEntity.status(404).body("Employee not found");
        }
    }
}
