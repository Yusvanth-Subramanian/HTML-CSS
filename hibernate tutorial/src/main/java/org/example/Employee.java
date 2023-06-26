package org.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    private int id;

    private String name;

    @ManyToOne
    private Role  roleId;

    private double salary;

    @ManyToOne(cascade=CascadeType.ALL)
    private Department deptId;

    public Employee(){}

    public Employee(int id, String name, Role roleID, double salary, Department deptId) {
        this.id = id;
        this.name = name;
        this.roleId = roleID;
        this.salary = salary;
        this.deptId = deptId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Role getRoleID() {
        return roleId;
    }

    public void setRoleID(Role roleID) {
        this.roleId = roleID;
    }

    public Department getDeptId() {
        return deptId;
    }

    public void setDeptId(Department deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleID=" + roleId +
                ", salary=" + salary +
                ", deptId=" + deptId +
                '}';
    }
}
