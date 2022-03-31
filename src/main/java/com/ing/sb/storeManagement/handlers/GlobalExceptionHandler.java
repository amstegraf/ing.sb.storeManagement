package com.ing.sb.storeManagement.handlers;

import com.ing.sb.storeManagement.dtos.GenericResponseDTO;
import com.ing.sb.storeManagement.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<GenericResponseDTO> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GenericResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponseDTO handleMethodArgumentException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errors = new StringBuilder();

        result.getFieldErrors().forEach(error ->
                errors
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ")
        );

        return new GenericResponseDTO(HttpStatus.BAD_REQUEST.value(), errors.toString());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public GenericResponseDTO handleConstraintViolation(DataIntegrityViolationException ex) {
        return new GenericResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getCause().getCause().getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public GenericResponseDTO handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return new GenericResponseDTO(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
    }
}
