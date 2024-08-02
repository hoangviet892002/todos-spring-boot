package com.example.springboot.CRUD.repository;


import com.example.springboot.CRUD.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {


}
