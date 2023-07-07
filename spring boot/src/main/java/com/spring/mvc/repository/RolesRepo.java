package com.spring.mvc.repository;

import com.spring.mvc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Role,Integer> {
}
