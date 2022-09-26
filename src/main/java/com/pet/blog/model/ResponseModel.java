package com.pet.blog.model;

public class ResponseModel {

    private int statusCode;
    private String status;
    private String message;

    public static ResponseModel toModel(int statusCode, String status, String message) {
        ResponseModel model = new ResponseModel();
        model.setStatusCode(statusCode);
        model.setStatus(status);
        model.setMessage(message);
        return model;
    }
    
    public ResponseModel() {};

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}