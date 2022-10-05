DELIMITER #
create procedure qry_emp(
 IN p_empnum int,
 OUT p_empname varchar(30),
 OUT p_empsalary int
)
BEGIN
 select empName, empSalary INTO p_empname, p_empsalary FROM employee WHERE empID = p_empnum;
END #
DELIMITER ;