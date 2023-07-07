package com.spring.boot.service;

import com.spring.boot.dto.AddEmployeeDTO;
import com.spring.boot.dto.ShowEmployeeDTO;
import com.spring.boot.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public void save(Employee employee, AddEmployeeDTO employeeDTO);
    public void save(Employee employee);

    public List<ShowEmployeeDTO> getAllEmployees();

    public Employee getEmployeeById(int id);

    public void deleteEmployeeById(int id);

    public Employee findByEmail(String email);
}
