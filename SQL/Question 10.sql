SELECT e.name as "Employee Name" FROM Employee e JOIN Salary s on e.emp_id = s.emp_id WHERE s.amount > 400000