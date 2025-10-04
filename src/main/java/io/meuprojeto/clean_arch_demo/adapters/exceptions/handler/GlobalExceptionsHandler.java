package io.meuprojeto.clean_arch_demo.adapters.exceptions.handler;

import io.meuprojeto.clean_arch_demo.adapters.exceptions.ExceptionResponse;
import io.meuprojeto.clean_arch_demo.usecase.exceptions.NotExistUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(NotExistUserException.class)
    public ResponseEntity<ExceptionResponse> handleNotExistingUserException(NotExistUserException ex) {
        ExceptionResponse response = new ExceptionResponse("User not found", ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }
}
