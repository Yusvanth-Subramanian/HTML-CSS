import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeManagement {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mariadb://localhost:3306/Employee";
        String username = "root";
        String password = "root";

        try {

            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            //System.out.println("Connected to the database!");

            String query = "SELECT * FROM Employee";

            ResultSet resultSet = statement.executeQuery(query);

            List<Employee> employees = new ArrayList<>();

            while (resultSet.next()) {
                String firstName = resultSet.getString("username");
                String email = resultSet.getString("email");
                Date hireDate = resultSet.getDate("joining_date");
                String salary = resultSet.getString("salary");
                String department = resultSet.getString("department");
                String phone = resultSet.getString("phone");
                LocalDate hireLocalDate = hireDate.toLocalDate();
                Employee employee = new Employee(firstName,email,hireLocalDate,phone,salary,department);
                employees.add(employee);
                //System.out.println(username+" "+email+" "+" "+hireDate+" "+salary+" "+department+" "+phone+"\n\n\n\n");

            }

            //System.out.println(employees);
            System.out.println("Employees whose name starts with D : ");
            employees.stream().filter(i->i.getName().startsWith("D")).forEach(System.out::println);

            System.out.println("\n\nEmployees who did not update their mobile number : ");
            employees.stream().filter(i->i.getPhone()==null).forEach(System.out::println);

            System.out.println("\n\nEmployees who belong to QA with salary > 10000 : ");
            employees.stream().filter(i->Integer.valueOf(i.getSalary())>10000 && i.getDepartment().equals("QA")).forEach(System.out::println);

            System.out.println("\n\nEmployees who belong to IT : ");
            employees.stream().filter(i->i.getDepartment().equals("IT")).forEach(System.out::println);

            System.out.println("\n\nGiving 10% increment to employees who is DEV : ");
            System.out.println("Before Incrementing : ");
            employees.stream().filter(i->i.getDepartment().equals("DEV")).forEach(System.out::println);
            System.out.println("After Incrementing : ");
            employees.stream().filter(i->i.getDepartment().equals("DEV")).map(i->new Employee(i.getName(),i.getEmail(),i.getJoiningDate(),i.getPhone(),(Integer.valueOf(i.getSalary())+(0.1*Integer.valueOf(i.getSalary())))+"",i.getDepartment())).forEach(System.out::println);

            System.out.println("\n\nEmployees joined between 01-Feb-2021 and 01-Apr-2021");
            LocalDate startDate = LocalDate.of(2021, 2, 1);
            LocalDate endDate = LocalDate.of(2021, 4, 1);
            employees.stream()
                    .filter(e -> e.getJoiningDate().isAfter(startDate) && e.getJoiningDate().isBefore(endDate))
                    .forEach(System.out::println);

            System.out.println("\n\nLowest Salary of an employee : ");
            int min=Integer.MAX_VALUE;
            System.out.println(employees.stream().map(Employee::getSalary).map(i->Integer.valueOf(i)).min(Integer::compare));

            System.out.println("\n\n3 recently joined Employees : ");
            employees.sort(Comparator.comparing(Employee::getJoiningDate).reversed());
            employees.stream().limit(3).forEach(System.out::println);

            System.out.println("\n\nSum of all salary joined in Feb 2021");
            LocalDate febStrart = LocalDate.of(2021,2,1);
            LocalDate febEnd = LocalDate.of(2021,2,28);
            List<String>sal=employees.stream().filter(i->i.getJoiningDate().isBefore(febEnd)&&i.getJoiningDate().isAfter(febStrart)).map(Employee::getSalary).collect(Collectors.toList());
            int res = 0;
            for(String s:sal){
                res+=Integer.valueOf(s);
            }
            System.out.println(res);

            LocalDate febMid = LocalDate.of(2021,5,14);
            System.out.println("\n\nAverage salary for employee joined on 14-Mar-2021");
            sal = employees.stream().filter(i->i.getJoiningDate().isEqual(febMid)).map(Employee::getSalary).collect(Collectors.toList());
            res=0;
            for(String s:sal){
                res+=Integer.valueOf(s);
            }
            System.out.println(res/sal.size());

            System.out.println("\n\nData map with Salary and employees count");

            Map<String,Integer> map1 = new HashMap<>();
            List<Employee> dev = employees.stream().filter(i->i.getDepartment().equals("DEV")).collect(Collectors.toList());
            List<Employee> qa = employees.stream().filter(i->i.getDepartment().equals("QA")).collect(Collectors.toList());
            List<Employee> it = employees.stream().filter(i->i.getDepartment().equals("IT")).collect(Collectors.toList());
            List<Employee> man = employees.stream().filter(i->i.getDepartment().equals("MANAGER")).collect(Collectors.toList());

            for(var x:employees){
                String salary=x.getSalary();
                if(map1.containsKey(salary)){
                    map1.put(salary,map1.get(salary)+1);
                }
                else
                    map1.put(salary,1);
            }
            System.out.println(map1);

            System.out.println("\n\na data map with employee records grouped by department");
            Map<String,List<Employee>> map2 = new HashMap<>();
            map2.put("DEV",dev);
            map2.put("QA", qa);
            map2.put("IT",it);
            map2.put("MANAGER", man);
            System.out.println(map2);

            System.out.println("\n\na data map with department and their salary");
//            int =0,qaSalary=0,itSalary=0,managerSalary=0;
            List<String> devSalary= new ArrayList<>();
            List<String> qaSalary= new ArrayList<>();
            List<String> itSalary= new ArrayList<>();
            List<String> managerSalary= new ArrayList<>();
            for(String s:map2.keySet()){
                List<Employee> emp = map2.get(s);
                for(Employee e:emp){
                    if(s.equals("DEV"))
                        devSalary.add(e.getSalary());
                    else if(s.equals("QA"))
                        qaSalary.add(e.getSalary());
                    else if(s.equals("IT"))
                        itSalary.add(e.getSalary());
                    else
                        managerSalary.add(e.getSalary());
                }
            }
            Map<String,List<String>> map3 = new HashMap<>();
            map3.put("DEV",devSalary);
            map3.put("QA",qaSalary);
            map3.put("IT",itSalary);
            map3.put("MANAGER",managerSalary);
            System.out.println(map3);

            System.out.println("\n\n Most highest paid by category");
            Map<String ,Employee> map4 = new HashMap<>();
            int maxDev=0,maxQa=0,maxIt=0,maxManager=0;
            Employee maxDevEmployee = null,maxQaEmployee = null,maxItEmployee = null,maxManagerEmployee = null;
            for(Employee e:map2.get("DEV")){

                if(e.getSalary()!=null&&Integer.valueOf(e.getSalary())>maxDev){
                    maxDev=Integer.valueOf(e.getSalary());
                    maxDevEmployee=e;
                }
            }
            for(Employee e:map2.get("QA")){
                if(e.getSalary()!=null&&Integer.valueOf(e.getSalary())>maxQa){
                    maxQa=Integer.valueOf(e.getSalary());
                    maxQaEmployee=e;
                }
            }
            for(Employee e:map2.get("IT")){
                if(e.getSalary()!=null&&Integer.valueOf(e.getSalary())>maxIt){
                    maxIt=Integer.valueOf(e.getSalary());
                    maxItEmployee=e;
                }
            }
            for(Employee e:map2.get("MANAGER")){
                if(e.getSalary()!=null&&Integer.valueOf(e.getSalary())>maxManager){
                    maxManager=Integer.valueOf(e.getSalary());
                    maxManagerEmployee=e;
                }
            }
            map4.put("DEV",maxDevEmployee);
            map4.put("QA",maxQaEmployee);
            map4.put("IT",maxItEmployee);
            map4.put("MANAGER",maxManagerEmployee);

            System.out.println(map4);
            
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
