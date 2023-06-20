package com.example.demo.tools.exception.models;

import java.util.Date;


public class ErrorDetail {
    private Boolean error=true;
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetail(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ErrorDetail() {
    }

    public Boolean getError() {
        return error;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
