package com.example.springboot.CRUD.controllers;

import com.example.springboot.CRUD.DTO.ListDTO;
import com.example.springboot.CRUD.reponse.ReponseDTO;
import com.example.springboot.CRUD.DTO.TodoDTO;
import com.example.springboot.CRUD.models.enums.todoStatus;
import com.example.springboot.CRUD.services.impl.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/todos")
@Tag(name = "Todo Controller", description = "Todo Controller")
public class ToDoController {




    @Autowired
    private TodoService todoService;




    @Operation(summary = "Create a new todo", description = "Create a new todo")
    @PostMapping
    public ResponseEntity<ReponseDTO> createTodo(@RequestBody TodoDTO todoDTO) {
        try {
            TodoDTO todo = todoService.create(todoDTO);
            ReponseDTO reponseDTO = new ReponseDTO("Successfully", true, todo);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.CREATED);
        }
        catch (Exception e) {
            ReponseDTO reponseDTO = new ReponseDTO("Invalid request", false, null);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get todos", description = "Get todos with page number")
    @GetMapping
    public ResponseEntity<ReponseDTO> getTodos(
            @RequestParam(value = "page", defaultValue = "1") String page
    ) {
        try {
            int pageInt = Integer.parseInt(page);
            ListDTO list = todoService.getTodos(page);
            ReponseDTO reponseDTO = new ReponseDTO("Successfully", true, list);

            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.OK);
        } catch (NumberFormatException e) {

            ReponseDTO reponseDTO = new ReponseDTO("Invalid page number", false, null);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get todo by id", description = "Get todo by id")
    @GetMapping("/{id}")
    public ResponseEntity<ReponseDTO> getTodoById(@Parameter( example = "66ab0bbdcf77b5553fe6c019")  @PathVariable String id) {
        try {
            TodoDTO todo = todoService.getTodoById(id);
            if (todo == null) {
                ReponseDTO reponseDTO = new ReponseDTO("Todo not found", false, null);
                return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.NOT_FOUND);
            }
            ReponseDTO reponseDTO = new ReponseDTO("Successfully", true, todo);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ReponseDTO reponseDTO = new ReponseDTO("Invalid request", false, null);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.BAD_REQUEST);
        }
    }
//
    @Operation(summary = "Update status of todo", description = "Update status of todo")
    @PatchMapping("/{id}")
    public ResponseEntity<ReponseDTO> updateStatus(
            @Parameter(example = "PENDING") @RequestParam todoStatus status,
            @Parameter(example = "66ab0bbdcf77b5553fe6c019") @PathVariable String id


    ) {
        try {
            TodoDTO todo = todoService.getTodoById(id);
            if (todo == null) {
                ReponseDTO reponseDTO = new ReponseDTO("Todo not found", false, null);
                return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.NOT_FOUND);
            }
            todo.setStatus(status);
            todoService.update(todo);
            ReponseDTO reponseDTO = new ReponseDTO("Successfully", true, todo);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ReponseDTO reponseDTO = new ReponseDTO("Invalid request", false, null);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update important of todo", description = "Update important of todo")
    @PatchMapping("/{id}/important")
    public ResponseEntity<ReponseDTO> updateImportant(@Parameter(example = "66ab0bbdcf77b5553fe6c019")@PathVariable String id) {
        try {
            TodoDTO todo = todoService.getTodoById(id);
            if (todo == null) {
                ReponseDTO reponseDTO = new ReponseDTO("Todo not found", false, null);
                return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.NOT_FOUND);
            }
            todo.setImportant(!todo.isImportant());
            todoService.update(todo);
            ReponseDTO reponseDTO = new ReponseDTO("Successfully", true, todo);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ReponseDTO reponseDTO = new ReponseDTO("Invalid request", false, null);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update information of todo", description = "Update information of todo")
    @PutMapping("/{id}")
    public ResponseEntity<ReponseDTO> updateInformation (@Parameter(example = "66ab0bbdcf77b5553fe6c019") @PathVariable String id, @RequestBody TodoDTO todoDTO) {
        try {
            TodoDTO todo = todoService.getTodoById(id);
            if (todo == null) {
                ReponseDTO reponseDTO = new ReponseDTO("Todo not found", false, null);
                return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.NOT_FOUND);
            }
            todo.setTitle(todoDTO.getTitle());
            todo.setDescription(todoDTO.getDescription());
            todoService.update(todo);
            ReponseDTO reponseDTO = new ReponseDTO("Successfully", true, todo);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ReponseDTO reponseDTO = new ReponseDTO("Invalid request", false, null);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete todo", description = "Delete todo")
    @DeleteMapping("/{id}")
    public ResponseEntity<ReponseDTO> deleteTodo (@PathVariable String id) {
        try {
            TodoDTO todo = todoService.getTodoById(id);
            if (todo == null) {
                ReponseDTO reponseDTO = new ReponseDTO("Todo not found", false, null);
                return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.NOT_FOUND);
            }
            todoService.delete(id);
            ReponseDTO reponseDTO = new ReponseDTO("Successfully", true, null);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ReponseDTO reponseDTO = new ReponseDTO("Invalid request", false, null);
            return new ResponseEntity<ReponseDTO>(reponseDTO, HttpStatus.BAD_REQUEST);
        }
    }



}
