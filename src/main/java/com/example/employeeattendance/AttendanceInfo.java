package com.example.employeeattendance;

import java.sql.Date;
import java.sql.Time;

public class AttendanceInfo {
    private int id;
    private String name;
    private Date date;
    private Time checkIn;
    private Time checkOut;
    private double overtime;

    public AttendanceInfo(int id, String name, Date date, Time checkIn, Time checkOut, double overtime) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.overtime = overtime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Time getCheckIn() {
        return checkIn;
    }

    public Time getCheckOut() {
        return checkOut;
    }

    public double getOvertime() {
        return overtime;
    }
}


