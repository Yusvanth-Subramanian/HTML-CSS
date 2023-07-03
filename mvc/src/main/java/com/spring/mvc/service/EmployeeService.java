package com.spring.mvc.service;

import com.spring.mvc.dto.AddEmployeeDTO;
import com.spring.mvc.dto.ShowEmployeeDTO;
import com.spring.mvc.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public void save(Employee employee, AddEmployeeDTO employeeDTO);
    public void save(Employee employee);

    public List<ShowEmployeeDTO> getAllEmployees();

    public Employee getEmployeeById(int id);

    public void deleteEmployeeById(int id);

    public Employee findByEmail(String email);
}
