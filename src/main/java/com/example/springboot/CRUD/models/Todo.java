package com.example.springboot.CRUD.models;


import com.example.springboot.CRUD.models.enums.todoStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "todos")
public class Todo {
    @Id
    private String id;

    private String title;

    private String description;

    @Builder.Default
    private todoStatus status = todoStatus.PENDING;

    @Builder.Default
    private boolean important = false;

    @Builder.Default
    private Date createdAt = new Date();

    @Builder.Default
    private Date updatedAt = new Date();

}
