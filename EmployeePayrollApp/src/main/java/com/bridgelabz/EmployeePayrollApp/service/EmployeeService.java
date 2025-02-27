package com.bridgelabz.EmployeePayrollApp.service;

import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
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
        log.info("Fetching employee by ID: {}", id);
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> updateEmployee(int id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        existingEmployee.ifPresent(employee -> {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            employeeRepository.save(employee);
            log.info("Employee updated successfully with ID: {}", id);
        });
        return existingEmployee;
    }

    @Override
    public boolean deleteEmployee(int id) {
        log.info("Deleting employee with ID: {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            log.info("Employee deleted successfully with ID: {}", id);
            return true;
        }
        log.warn("Employee not found with ID: {}", id);
        return false;
    }
}
