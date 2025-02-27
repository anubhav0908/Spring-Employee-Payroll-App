package com.bridgelabz.EmployeePayrollApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeDTO {

    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "Name must start with a capital letter and contain only letters")
    private String name;

    @Min(value = 5000, message = "Salary must be at least 5000")
    private double salary;
}
