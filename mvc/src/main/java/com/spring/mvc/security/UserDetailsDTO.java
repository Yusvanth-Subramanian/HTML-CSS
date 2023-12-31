package com.spring.mvc.security;

import com.spring.mvc.entity.Role;
import com.spring.mvc.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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

        String[] authStrings = new String[roles.size()];
        for(int i=0;i<roles.size();i++)
        {
            authStrings[i]=roles.get(i).getName();
        }

        for(String authString : authStrings) {
            authorities.add(new SimpleGrantedAuthority(authString));
        }
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
