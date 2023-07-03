package com.spring.mvc.service;

import com.spring.mvc.dto.AddEmployeeDTO;
import com.spring.mvc.dto.ShowEmployeeDTO;
import com.spring.mvc.entity.Employee;
import com.spring.mvc.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void save(Employee employee, AddEmployeeDTO employeeDTO) {
        employee.setAddress(employeeDTO.getAddress());
        employee.setDesignation(employeeDTO.getDesignation());

        employee.setDepartment(employeeDTO.getDepartment());
        System.out.println(employee);
        employeeRepo.save(employee);
    }

    @Override
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public List<ShowEmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        List<ShowEmployeeDTO> employeeDTOS = new ArrayList<>();
        for(Employee e:employees){
            ShowEmployeeDTO showEmployeeDTO = new ShowEmployeeDTO();
            showEmployeeDTO.setAddress(e.getAddress());
            showEmployeeDTO.setDepartment(e.getDepartment());
            showEmployeeDTO.setDesignation(e.getDesignation());
            showEmployeeDTO.setEmail(e.getEmail());
            showEmployeeDTO.setId(e.getId());
            showEmployeeDTO.setMobileNumber(e.getMobileNumber());
            showEmployeeDTO.setName(e.getName());
            employeeDTOS.add(showEmployeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id);
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }
}
