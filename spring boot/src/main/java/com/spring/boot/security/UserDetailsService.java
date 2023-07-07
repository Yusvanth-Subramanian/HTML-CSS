package com.spring.boot.security;

import com.spring.boot.dto.UserDetailsDTO;
import com.spring.boot.entity.Employee;
import com.spring.boot.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    EmployeeRepo employeeRepo;

    @Autowired
    public UserDetailsService(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByEmail(username);
        if(employee == null){
            return null;
        }
        return new UserDetailsDTO(employee);
    }
}
