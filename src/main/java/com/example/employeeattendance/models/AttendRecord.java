package com.example.employeeattendance.models;

public class AttendRecord {
    private int id;
    private int totalAbsent;
    private int late;
    private int earlyCheckout;
    private int inTime;
    private int overTime;
    private int approved;
    private String status;

    public AttendRecord() {
    }


    public AttendRecord(int id, int inTime, int late, int absent, int approved, String status, int overtime) {
        this.id = id;
        this.inTime = inTime;
        this.late = late;
        this.totalAbsent = absent;
        this.approved = approved;
        this.status = status;
        this.overTime = overtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalAbsent() {
        return totalAbsent;
    }

    public void setTotalAbsent(int totalAbsent) {
        this.totalAbsent = totalAbsent;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getEarlyCheckout() {
        return earlyCheckout;
    }

    public void setEarlyCheckout(int earlyCheckout) {
        this.earlyCheckout = earlyCheckout;
    }

    public int getInTime() {
        return inTime;
    }

    public void setInTime(int inTime) {
        this.inTime = inTime;
    }

    public int getOverTime() {
        return overTime;
    }

    public void setOverTime(int overTime) {
        this.overTime = overTime;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendRecord{" +
                "id=" + id +
                ", totalAbsent=" + totalAbsent +
                ", late=" + late +
                ", earlyCheckout=" + earlyCheckout +
                ", inTime=" + inTime +
                ", overTime=" + overTime +
                ", approved=" + approved +
                ", status='" + status + '\'' +
                '}';
    }
}
