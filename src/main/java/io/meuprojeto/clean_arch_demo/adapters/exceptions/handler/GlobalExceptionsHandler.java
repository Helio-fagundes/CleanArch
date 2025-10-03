package io.meuprojeto.clean_arch_demo.adapters.exceptions.handler;

import io.meuprojeto.clean_arch_demo.adapters.exceptions.ExceptionResponse;
import io.meuprojeto.clean_arch_demo.usecase.exceptions.NotExistingUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(NotExistingUserException.class)
    public ResponseEntity<ExceptionResponse> handleNotExistingUserException(NotExistingUserException ex) {
        ExceptionResponse response = new ExceptionResponse("User not found", ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }
}
