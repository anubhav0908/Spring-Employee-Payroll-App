package com.bridgelabz.EmployeePayrollApp.controller;

import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelabz.EmployeePayrollApp.model.Employee;
import com.bridgelabz.EmployeePayrollApp.service.IEmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeeService;

    @GetMapping("/")
    public String home() {
        return "Welcome to Employee Payroll Service!";
    }

    // Create Employee
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(employee);
    }

    // Get All Employees
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Get Employee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);

        return employee
                .<ResponseEntity<?>>map(ResponseEntity::ok)  // Explicitly specify generic type
                .orElseGet(() -> ResponseEntity.status(404).body("Employee not found"));
    }


    // Update Employee
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, employeeDTO);

        return updatedEmployee
                .<ResponseEntity<?>>map(ResponseEntity::ok)  // Explicitly specify generic type
                .orElseGet(() -> ResponseEntity.status(404).body("Employee not found"));
    }


    // Delete Employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            return ResponseEntity.ok("Employee deleted with ID: " + id);
        } else {
            return ResponseEntity.status(404).body("Employee not found");
        }
    }
}
