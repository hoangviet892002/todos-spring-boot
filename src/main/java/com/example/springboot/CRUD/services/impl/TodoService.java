package com.example.springboot.CRUD.services.impl;

import com.example.springboot.CRUD.DTO.ListDTO;
import com.example.springboot.CRUD.DTO.TodoDTO;
import com.example.springboot.CRUD.mapper.TodoMapper;
import com.example.springboot.CRUD.models.Todo;
import com.example.springboot.CRUD.repository.TodoRepository;
import com.example.springboot.CRUD.services.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService implements ITodoService {


    @Autowired
    private  TodoRepository todoRepository;

    @Autowired
    private  TodoMapper todoMapper;

    private final int sizePage = 5;



    @Override
    public ListDTO getTodos(String page) {
        ListDTO listDTO = new ListDTO(0,null);
        try {
            int pageInt = Integer.parseInt(page);
            List<Todo> AllTodo = todoRepository.findAll();
            int totalTodos = AllTodo.size();
            int startIndex = (pageInt - 1) * sizePage;
            int endIndex = Math.min(pageInt * sizePage, totalTodos);
            if (startIndex >= totalTodos) {
                return listDTO;
            }
            List<Todo> todos = AllTodo.subList(startIndex, endIndex);
            List<TodoDTO> todoDTOs = todoMapper.toTodoDTOs(todos);
            listDTO.setTotal(totalTodos);
            listDTO.setData(todoDTOs);
            return  listDTO;
        } catch (Exception e) {
            return listDTO;
        }
    }

    @Override
    public TodoDTO create(TodoDTO todoDTO) {
        Todo todo = todoMapper.toModel(todoDTO);
        Todo newTodo = todoRepository.save(todo);
        return todoMapper.toTodoDTO(newTodo);
    }

    @Override
    public TodoDTO getTodoById(String id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            return null;
        }
        return todoMapper.toTodoDTO(todo);
    }

    @Override
    public TodoDTO update(TodoDTO todo) {
        Todo todoModel = todoMapper.toModel(todo);
        Todo updatedTodo = todoRepository.save(todoModel);
        return todoMapper.toTodoDTO(updatedTodo);
    }

    @Override
    public void delete(String id) {
        todoRepository.deleteById(id);
    }
}
