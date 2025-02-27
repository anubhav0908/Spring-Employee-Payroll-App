package com.bridgelabz.EmployeePayrollApp.service;

import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelabz.EmployeePayrollApp.exception.EmployeeNotFoundException;
import com.bridgelabz.EmployeePayrollApp.model.Employee;
import com.bridgelabz.EmployeePayrollApp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j  // Lombok annotation for logging
@Service
public class EmployeeService implements IEmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating a new employee with name: {}", employeeDTO.getName());
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee created successfully with ID: {}", savedEmployee.getId());
        return savedEmployee;

    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees from database");
        return employeeRepository.findAll();
    }
    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return Optional.ofNullable(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found")));
    }

    @Override
    public Optional<Employee> updateEmployee(int id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return Optional.of(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        employeeRepository.delete(employee);
    }
}
