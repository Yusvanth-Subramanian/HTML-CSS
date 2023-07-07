package com.spring.mvc.controller;

import com.spring.mvc.dto.AddEmployeeDTO;
import com.spring.mvc.dto.LoginDTO;
import com.spring.mvc.entity.Employee;
import com.spring.mvc.exception.UserNotFoundException;
import com.spring.mvc.handler.ResponseHandler;
import com.spring.mvc.security.LoginMapper;
import com.spring.mvc.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
public class HomeController {

    LoginMapper loginMapper;
    EmployeeService employeeService;

    PasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(LoginMapper loginMapper,EmployeeService employeeService,PasswordEncoder passwordEncoder){
        this.loginMapper = loginMapper;
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request){

        String s = loginMapper.authenticate(loginDTO, response,request);

        if(s.equals("Authenticated"))
            return ResponseHandler.generateResponse("User Authenticated", HttpStatus.OK);
        else {

            return ResponseHandler.generateResponse("Invalid credentials",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        request.getSession().setAttribute("authenticatedUser","");
        return ResponseHandler.generateResponse("User Logged out",HttpStatus.OK);
    }

    @PostMapping("/add-employee")
    public ResponseEntity<Object> register(@RequestBody AddEmployeeDTO employee){
        //System.out.println(employee);
        Employee e = new Employee();
        String errorMsg="";
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern1 = Pattern.compile(pattern);

        if(employee.getName().equals(""))
            errorMsg+="Name field should not be empty!\n";

        else
            e.setName(employee.getName());

        if(employee.getEmail().endsWith("@gmail.com"))
            e.setEmail(employee.getEmail());
        else
            errorMsg+="Enter a valid email id!\n";

        if(pattern1.matcher(employee.getPassword()).matches()){
            if(employee.getPassword().equals(employee.getConfirmPassword()))
                e.setPassword(passwordEncoder.encode(employee.getPassword()));
            else
                errorMsg+="Please recheck the confirm password field!\n";
        }
        else
            errorMsg+="Password must contain : At least 8 characters long\n" +
                    "Contains at least one uppercase letter\n" +
                    "Contains at least one lowercase letter\n" +
                    "Contains at least one digit\n" +
                    "Contains at least one special character from the set @#$%^&+=\n" +
                    "Does not contain whitespace\n";

            e.setMobileNumber(employee.getMobileNumber());


        if(errorMsg.equals("")){
            employeeService.save(e,employee);
            return ResponseHandler.generateResponse("User added",HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse(errorMsg,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/home")
    public ResponseEntity<Object> home(){
        return ResponseHandler.generateResponse(employeeService.getAllEmployees(),"All users retrieved",HttpStatus.OK);
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<Object> view(@PathVariable("id")int id ) throws UserNotFoundException {

        Employee employee = employeeService.getEmployeeById(id);

        if(employee==null){
            throw new UserNotFoundException();
        }

        AddEmployeeDTO addEmployeeDTO = new AddEmployeeDTO();
        addEmployeeDTO.setEmail(employee.getEmail());
        addEmployeeDTO.setName(employee.getName());
        addEmployeeDTO.setMobileNumber(employee.getMobileNumber());
        addEmployeeDTO.setAddress(employee.getAddress());
        addEmployeeDTO.setDepartment(employee.getDepartment());
        addEmployeeDTO.setDesignation(employee.getDesignation());

        return ResponseHandler.generateResponse(addEmployeeDTO,"Retrieved user details",HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id")int id, HttpServletRequest request){

        if(employeeService.findByEmail((String)request.getSession().getAttribute("authenticatedUser"))==employeeService.getEmployeeById(id)){
            return ResponseHandler.generateResponse("Logged in user cannot be deleted",HttpStatus.BAD_REQUEST);
        }

        employeeService.deleteEmployeeById(id);
        return ResponseHandler.generateResponse("User deleted",HttpStatus.OK);
    }



    @PostMapping("/update/{id}")
    public ResponseEntity<Object> save(@PathVariable("id")int id , @RequestBody Employee employee){
        Employee employee1 = employeeService.getEmployeeById(id);

        String errorMsg="";
        if(employee.getName().equals(""))
            errorMsg+="Name field should not be empty!\n";

        else
            employee1.setName(employee.getName());

        if(employee.getEmail().endsWith("@gmail.com"))
            employee1.setEmail(employee.getEmail());
        else
            errorMsg+="Enter a valid email id!\n";

        if(errorMsg.equals("")){

            employee1.setDesignation(employee.getDesignation());
            employee1.setAddress(employee.getAddress());
            employee1.setDepartment(employee.getDepartment());
            employee1.setMobileNumber(employee.getMobileNumber());
            employeeService.save(employee1);
            return ResponseHandler.generateResponse(employee1,"User details updated",HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse(errorMsg,HttpStatus.BAD_REQUEST);
        }
    }
}
