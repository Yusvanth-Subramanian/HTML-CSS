SELECT  d.dept_name as "Department",e.name as "Employee Name" FROM Department d JOIN Employee e ON d.dept_id = e.dept_id ORDER BY d.dept_id, e.emp_id