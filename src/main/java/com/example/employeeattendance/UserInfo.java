package com.example.employeeattendance;

import java.sql.Date;

public class UserInfo {
    private int id;
    private String name;
    private String position;
    private String department;
    private int baseSalary;
    private int absent;
    private int checking;
    private int bonus;
    private int income;
    private Date date;
    private String firstname;
    private String lastname;
    public UserInfo(int id, String name, String position, String department, int baseSalary){
        this.id=id;
        this.name=name;
        this.baseSalary=baseSalary;
        this.position=position;
        this.department=department;
    }
    public UserInfo(int id, String name, String position, String department, Date date,String firstname, String lastname){
        this.id=id;
        this.name=name;
        this.position=position;
        this.department=department;
        this.date=date;
    }
    public UserInfo(int id, int absent, int bonus, int income, int checking){
        this.id=id;
        this.absent=absent;
        this.bonus=bonus;
        this.income=income;
        this.checking=checking;
    }

    public int getAbsent() {
        return absent;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public int getBonus() {
        return bonus;
    }

    public int getChecking() {
        return checking;
    }

    public int getIncome() {
        return income;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
