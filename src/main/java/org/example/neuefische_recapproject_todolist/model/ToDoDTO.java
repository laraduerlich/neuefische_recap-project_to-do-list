package org.example.neuefische_recapproject_todolist.model;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ToDoDTO(String description,
                      ToDoStatus status) {
}
