package com.example.employeeattendance.models;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Attendance {
    private int id;
    private LocalDate date;
    private String checkin;
    private String checkout;
    private String overtime;

    public Attendance() {
    }

    public Attendance(int id, LocalDate date, String checkin, String checkout, String overtime) {
        this.id = id;
        this.date = date;
        this.checkin = checkin;
        this.checkout = checkout;
        this.overtime = overtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                ", overtime='" + overtime + '\'' +
                '}';
    }
}
