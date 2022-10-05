DELIMITER #
create procedure upd_emp_salary(
 IN p_salary int,
 IN p_empnum int
)
BEGIN
 update employee SET empSalary = p_salary WHERE empID = p_empnum;
END #
DELIMITER ;