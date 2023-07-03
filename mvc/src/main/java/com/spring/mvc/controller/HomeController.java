package com.spring.mvc.controller;

import com.spring.mvc.dto.AddEmployeeDTO;
import com.spring.mvc.dto.LoginDTO;
import com.spring.mvc.entity.Employee;
import com.spring.mvc.security.LoginMapper;
import com.spring.mvc.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Controller
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

    @RequestMapping("/")
    public String global(){
        return "redirect:/home";
    }

    @GetMapping("/create")
    public String create(Model model){
        AddEmployeeDTO employee = new AddEmployeeDTO();
        model.addAttribute("employee",employee);
        return "create-employee";
    }

    @PostMapping("/validate-employee")
    public String register(@ModelAttribute("employee") AddEmployeeDTO employee,Model model){
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
            return "redirect:/home";
        }
        else {
            model.addAttribute("errorMsg", errorMsg);
            return "create-employee";
        }

    }

    @GetMapping("/login")
    public String login(Model model){
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("login",loginDTO);
        return "login";
    }

    @RequestMapping("/authenticateUser")
    public String authenticateUser(@ModelAttribute("login")LoginDTO loginDTO, HttpServletResponse response, Model model, HttpServletRequest request){

        String s = loginMapper.authenticate(loginDTO, response,request);

        if(s.equals("Authenticated"))
            return "redirect:/home";
        else {
            model.addAttribute("errorMsg","Invalid Credentials . Login again!!!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("authenticatedUser","");
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("employees",employeeService.getAllEmployees());
        return "home";
    }


    @GetMapping("/view/{id}")
    public String view(@PathVariable("id")String id , Model model){

        Employee employee = employeeService.getEmployeeById((Integer.parseInt(id)));

        AddEmployeeDTO addEmployeeDTO = new AddEmployeeDTO();
        addEmployeeDTO.setEmail(employee.getEmail());
        addEmployeeDTO.setName(employee.getName());
        addEmployeeDTO.setMobileNumber(employee.getMobileNumber());
        addEmployeeDTO.setAddress(employee.getAddress());
        addEmployeeDTO.setDepartment(employee.getDepartment());
        addEmployeeDTO.setDesignation(employee.getDesignation());

        model.addAttribute("employee",addEmployeeDTO);

        return "view-employee";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id,HttpServletRequest request,Model model){
        ;
        if(employeeService.findByEmail((String)request.getSession().getAttribute("authenticatedUser"))==employeeService.getEmployeeById(id)){
            return "Error-page";
        }
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "confirm-delete";
    }

    @GetMapping("/confirm-delete/{id}")
    public String confirm(@PathVariable("id")int id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id")int id,Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "update-page";
    }

    @PostMapping("/save-changes/{id}")
    public String save(@PathVariable("id")int id ,@ModelAttribute("employee")Employee employee,Model model){
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
            return "redirect:/home";
        }
        else {
            model.addAttribute("errorMsg", errorMsg);
            return "update-page";
        }
    }

}
