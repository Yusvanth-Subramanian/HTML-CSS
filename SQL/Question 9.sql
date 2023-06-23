SELECT d.dept_name, COUNT(e.emp_id) AS "Number of Employees" FROM Department d JOIN Employee e ON e.dept_id = d.dept_id WHERE e.hire_date BETWEEN '2023-06-01' AND '2023-06-31' GROUP BY d.dept_id, d.dept_name