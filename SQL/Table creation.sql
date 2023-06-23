
CREATE TABLE Employee(
	emp_id INT,
    name VARCHAR(50),
    hire_date DATE,
    job_title VARCHAR(50),
    dept_id INT,
    reports_to INT,
    PRIMARY KEY (emp_id)
);

CREATE TABLE Address(
	addresss_id INT,
    city VARCHAR(50),
    emp_id INT
);
CREATE TABLE Salary(
	amount INT,
    start_date DATE,
    end_date DATE,
    emp_id INT
);
CREATE TABLE EmployeeLeave(
	start_date DATE,
    end_date DATE,
    emp_id INT
);    