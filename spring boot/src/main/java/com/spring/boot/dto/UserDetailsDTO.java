package com.spring.boot.dto;

import com.spring.boot.entity.Role;
import com.spring.boot.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class UserDetailsDTO implements UserDetails {

    private int id;
    private String email;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
    private List<Role> roles;

    public UserDetailsDTO(){}

    public UserDetailsDTO(Employee employee) {
        this.id= employee.getId();
        this.email= employee.getEmail();
        this.password= employee.getPassword();
        this.active=true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        this.roles = employee.getRoles();

        String[] authStrings ;
        authStrings = IntStream.range(0, roles.size())
                .mapToObj(i -> roles.get(i).getName())
                .toArray(String[]::new);


        authorities = Arrays.stream(authStrings)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


        this.authorities=authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", authorities=" + authorities +
                ", roles=" + roles +
                '}';
    }


}
