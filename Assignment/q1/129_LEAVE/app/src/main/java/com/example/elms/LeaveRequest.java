package com.example.elms;

public class LeaveRequest {
    private int id;
    private String date;
    private String reason;
    private String status;

    // Constructor
    public LeaveRequest(String date, String reason, String status) {
        this.date = date;
        this.reason = reason;
        this.status = status;
    }

    public LeaveRequest() {

    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
