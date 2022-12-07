package com.api.exercise.restapiexercisespring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.exercise.restapiexercisespring.data.dtos.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(final UserNotFoundException ex) {
        return new ResponseEntity<>(
            ErrorDTO.builder()
                .title("Invalid User")
                .detail(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .errorType(UserNotFoundException.class.getSimpleName())
                .errorCode("NF01")
                .build(),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistsException(final UserAlreadyExistsException ex){
        return new ResponseEntity<>(
            ErrorDTO.builder()
                .title("User Already Exists")
                .detail(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .errorType(UserAlreadyExistsException.class.getSimpleName())
                .errorCode("C01")
                .build(),
            HttpStatus.CONFLICT
        );
    }
}
