package com.satiate.bio.models;

/**
 * Created by Rishabh Bhatia on 10/7/2016.
 */
public class Error {

    private String name;
    private int status;
    private String code;
    private int statusCode;
    private String message;

    public Error() {

    }

    public Error(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}