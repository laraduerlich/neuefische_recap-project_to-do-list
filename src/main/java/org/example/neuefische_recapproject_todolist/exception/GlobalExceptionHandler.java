package org.example.neuefische_recapproject_todolist.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponsesDTO handleException(Exception exception) {
        return new ErrorResponsesDTO(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public ErrorResponsesDTO handleNotFoundException(NotFoundException exception) {
        return new ErrorResponsesDTO(exception.getMessage());
    }


}
