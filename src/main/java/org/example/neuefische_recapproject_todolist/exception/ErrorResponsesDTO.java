package org.example.neuefische_recapproject_todolist.exception;

public class ErrorResponsesDTO {

    private String message;

    public ErrorResponsesDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
