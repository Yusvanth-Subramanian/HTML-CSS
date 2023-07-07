package com.spring.boot.repository;

import com.spring.boot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    public Employee findByEmail(String email);

    public Employee findById(int id);

    public void deleteById(int id);
}
