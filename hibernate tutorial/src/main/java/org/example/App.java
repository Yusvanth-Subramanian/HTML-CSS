package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Configuration configuration = new Configuration().addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class).addAnnotatedClass(Role.class);

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mariadb://localhost:3306/alien?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "root");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MariaDBDialect");

        settings.put(Environment.SHOW_SQL, "true");

        settings.put(Environment.HBM2DDL_AUTO, "update");

        configuration.setProperties(settings);

        SessionFactory sessionFactory = configuration.buildSessionFactory();


        Session session = sessionFactory.openSession();

        // Question 1

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from "+"Employee");

        System.out.println("Question 1 : Fetch the records :");
        List<Employee> employees = query.list();

        for(Employee e:employees){
            System.out.println(e);
        }

        transaction.commit();


        // Question 2

        transaction = session.beginTransaction();
        System.out.println("\n\nQuestion 2 : Save Records");

        Role role = new Role(3,"ADMIN");
        Employee employee = session.get(Employee.class,3);
        employee.setRoleID(role);
        session.save(role);

        transaction.commit();


        // Question 3
        transaction = session.beginTransaction();
        System.out.println("\n\nQuestion 3 : Create new Department");

        Department department = new Department(3,"Platform");

        session.save(department);
        transaction.commit();


        // Question 4

        transaction = session.beginTransaction();
        System.out.println("\n\nQuestion 4: Map new department to employee");
        Employee employee1 = session.get(Employee.class,5);
        Department department1 = session.get(Department.class,3);
        employee1.setDeptId(department1);
        session.save(employee1);
        transaction.commit();


        // Question 5

        transaction = session.beginTransaction();
        System.out.println("\n\nQuestion 5 : Update the role name with id = 1 : ");
        Role role1 = session.get(Role.class,1);
        role1.setName("USER");
        transaction.commit();


        // Question 6
        transaction = session.beginTransaction();
        System.out.println("\n\nQuestion 6 : Delete the employee : ");
        Employee employee2 = session.get(Employee.class,5);
        session.delete(employee2);
        transaction.commit();
    }
}
