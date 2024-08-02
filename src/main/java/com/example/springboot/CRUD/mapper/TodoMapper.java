package com.example.springboot.CRUD.mapper;

import com.example.springboot.CRUD.DTO.TodoDTO;
import com.example.springboot.CRUD.models.Todo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class TodoMapper {
    public  TodoDTO toTodoDTO (Todo todo) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(todo.getId());
        todoDTO.setTitle(todo.getTitle());
        todoDTO.setDescription(todo.getDescription());
        todoDTO.setImportant(todo.isImportant());
        todoDTO.setStatus(todo.getStatus());
        return todoDTO;
    }
    public Todo toModel (TodoDTO todoDTO) {
        Todo todo = new Todo();
        todo.setId(todoDTO.getId());
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setImportant(todoDTO.isImportant());
        todo.setStatus(todoDTO.getStatus());
        return todo;
    }
    public  List<TodoDTO> toTodoDTOs (List<Todo> todos) {
        List<TodoDTO> todoDTOs = new ArrayList<>();
        for (Todo todo : todos) {
            todoDTOs.add(toTodoDTO(todo));
        }
        return todoDTOs;
    }
}
