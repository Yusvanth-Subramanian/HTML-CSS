SELECT e.name AS "Employee Name", SUM(s.amount) AS "Total Salary" FROM Employee e JOIN Salary s ON e.emp_id = s.emp_id WHERE YEAR(s.start_date) = 2023 GROUP BY e.emp_id, e.name
