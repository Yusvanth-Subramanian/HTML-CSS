package com.spring.boot.service;


import com.spring.boot.dto.AddEmployeeDTO;
import com.spring.boot.dto.ShowEmployeeDTO;
import com.spring.boot.entity.Employee;
import com.spring.boot.repository.EmployeeRepo;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void save(Employee employee, AddEmployeeDTO employeeDTO) {
        BeanUtils.copyProperties(employeeDTO,employee);
        employeeRepo.save(employee);
    }

    @Override
    public void save(Employee employee) {
        log.info("Saving new user");
        employeeRepo.save(employee);
    }

    @Override
    public List<ShowEmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll().stream().map(employee -> {
            ShowEmployeeDTO showEmployeeDTO = new ShowEmployeeDTO();
            BeanUtils.copyProperties(employee, showEmployeeDTO);
            return showEmployeeDTO;
        }).toList();
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
