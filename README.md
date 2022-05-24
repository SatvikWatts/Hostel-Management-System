# Hostel-Management-System

Requirements:
  Netbeans IDE
  MySql(preferably)
  JDBC
  
  Database Tables:
    1] create table login(
        uname varchar(100),
        pass varchar(100),
        is_warden boolean,
        primary key(uname)
       );
   2] create table mess(
        account_number varchar(100),
        mess_no varchar(100),
        dues boolean,
        primary key(account_number)
       );
   3] create table room(
        account_number varchar(100),
        room varchar(100),
        primary key(account_number)
       );
    4] create table stdid(
        account_number varchar(100),
        student_id varchar(100),
        primary key(account_number)
       );
    5] create table student(
        account_number varchar(100),
        stname varchar(100),
        c_no varchar(100),
        address varchar(100),
        dues is_room,
        primary key(account_number)
       );
       
