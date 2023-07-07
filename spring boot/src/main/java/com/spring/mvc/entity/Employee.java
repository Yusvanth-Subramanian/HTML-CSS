package com.spring.mvc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String designation;

    @Column
    private long mobileNumber;

    @Column
    private String address;

    @Column
    private String department;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private List<Role> roles;


    public Employee(String name, String email, String password, String designation, long mobileNumber, String address, String department, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.department = department;
        this.roles = roles;
    }


}
