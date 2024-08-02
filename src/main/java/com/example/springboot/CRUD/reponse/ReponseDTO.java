package com.example.springboot.CRUD.reponse;

public class ReponseDTO {
    private String message;
    private boolean status;
    private Object data;

    public ReponseDTO() {

    }

    public ReponseDTO(String message, boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
