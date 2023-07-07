package com.spring.boot.security;

import com.spring.boot.dto.LoginDTO;
import com.spring.boot.repository.EmployeeRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {

    UserDetailsService userDetailsService;
    EmployeeRepo employeeRepo;

    AuthenticationManager authenticationManager;

    @Autowired
    public LoginMapper(UserDetailsService userDetailsService, EmployeeRepo employeeRepo, AuthenticationManager authenticationManager){
        this.userDetailsService = userDetailsService;
        this.employeeRepo = employeeRepo;
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
            HttpSession session = request.getSession();
            session.setAttribute("authenticatedUser",loginDTO.getEmail());
            return "Authenticated";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Exception occurred";
        }
    }
}
