package com.bridgelabz.EmployeePayrollApp.service;

import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelabz.EmployeePayrollApp.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeePayrollService {

    Employee createEmployee(EmployeeDTO employeeDTO);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(int id);
    Optional<Employee> updateEmployee(int id, EmployeeDTO employeeDTO);
    void deleteEmployee(int id);
    
}
