create database jdbctest;
use jdbctest;

create table department(
    deptNum int primary key,
    deptName varchar(30) not null
);

insert into department
values
    (100, 'IT'),
    (200, 'Accounting'),
    (300, 'Marketing');

create table employee(
    empID int primary key,
    empName varchar(30) not null,
    empSalary int not null,
    empHiredate date not null,
    empDeptnum int not null,
    foreign key (empDeptnum) references department(deptNum)
);

insert into employee
values
    (1001, 'John', 56000, '2010-11-10', 100),
    (1002, 'Mary', 60000, '2010-05-23', 300),
    (1003, 'Tom', 48000, '2010-08-15', 100),
    (1004, 'Kevin', 52000, '2011-04-26', 200),
    (1005, 'Vincent', 45000, '2010-06-02', 200);
    
desc department;
desc employee;

select * from department;
select * from employee;

create table testBLOB(
    imageID int primary key,
    imageName varchar(30) not null,
    image blob not null
);
