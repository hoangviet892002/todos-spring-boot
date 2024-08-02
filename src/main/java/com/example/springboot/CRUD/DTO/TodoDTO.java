package com.example.springboot.CRUD.DTO;


import com.example.springboot.CRUD.models.enums.todoStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {

    @Id
    @Schema(hidden = true, description = "The id of the todo", example = "60f1b9b3b3b3b3b3b3b3b3b3")
    private String id;

    @Schema(description = "The title of the todo", example = "Buy groceries")
    private String title;

    @Schema(description = "The description of the todo", example = "Buy milk, eggs, and bread")
    private String description;

    @Builder.Default
    @Schema(description = "The status of the todo", example = "PENDING")
    private todoStatus status = todoStatus.PENDING;

    @Builder.Default
    @Schema(description = "The importance of the todo", example = "false")
    private boolean important = false;

    @Builder.Default
    @Schema(hidden = true)
    private Date createdAt = new Date();

    @Builder.Default
    @Schema(hidden = true)
    private Date updatedAt = new Date();

}
