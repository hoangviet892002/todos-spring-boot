package com.example.springboot.CRUD.DTO;

import java.util.List;

public class ListDTO {
    private int total;
    private Object data;

    public ListDTO(int total, Object data) {
        this.total = total;
        this.data = data;
    }

    public ListDTO() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
