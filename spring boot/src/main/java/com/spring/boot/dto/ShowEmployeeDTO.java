package com.spring.boot.dto;

import lombok.Builder;

@Builder
public class ShowEmployeeDTO {

    private int id;

    private String name;

    private String email;

    private String designation;

    private long mobileNumber;

    private String address;

    private String department;

    public ShowEmployeeDTO(){}

    public ShowEmployeeDTO(int id, String name, String email, String designation, long mobileNumber, String address, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.department = department;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "ShowEmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
