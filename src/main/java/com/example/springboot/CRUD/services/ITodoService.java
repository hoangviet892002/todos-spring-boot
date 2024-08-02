package com.example.springboot.CRUD.services;

import com.example.springboot.CRUD.DTO.ListDTO;
import com.example.springboot.CRUD.DTO.TodoDTO;

import java.util.List;

public interface ITodoService {

    public TodoDTO create(TodoDTO todo);
    public ListDTO getTodos(String page);
    public TodoDTO getTodoById(String id);
    public TodoDTO update(TodoDTO todo);
    public void delete(String id);

}
