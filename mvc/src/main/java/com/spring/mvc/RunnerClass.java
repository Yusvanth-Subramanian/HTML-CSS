package com.spring.mvc;

import com.spring.mvc.repository.RolesRepo;
import com.spring.mvc.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RunnerClass implements CommandLineRunner {

    EmployeeRepo employeeRepo;
    RolesRepo rolesRepo;
    PasswordEncoder passwordEncoder;


    @Autowired
    public RunnerClass(EmployeeRepo employeeRepo, RolesRepo rolesRepo, PasswordEncoder passwordEncoder){
        this.employeeRepo = employeeRepo;
        this.rolesRepo = rolesRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

//        Role roleAdmin = new Role(1,"ADMIN",null);
//
//        Role roleUser = new Role(2,"USER",null);
//
//        rolesRepo.save(roleAdmin);
//        rolesRepo.save(roleUser);
//
//        User user1 = new User("Yusvanth","yusvanths@gmail.com",passwordEncoder.encode("123"), Arrays.asList(roleUser,roleAdmin));
//
//        User user2 = new User("Vimalkumar","Vimalkumar@gmail.com",passwordEncoder.encode("123"), Arrays.asList(roleAdmin));
//
//        userRepo.save(user1);
//        userRepo.save(user2);
    }
}
