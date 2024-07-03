package com.MarcosEcommerce.MarcosEcommerce.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.add(fieldError.getField() + ":" + fieldError.getDefaultMessage())
                );
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                errors,
                "Error on validation",
                request.getDescription(false)
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFoundException(
            ProductNotFoundException ex,
            WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                List.of(ex.getMessage()),
                "Product not found",
                request.getDescription(false)
        );
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(EmptyDtoException.class)
    protected ResponseEntity<Object> handleEmptyDtoException(
            EmptyDtoException ex,
            WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                List.of(ex.getMessage()),
                "Empty payload",
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
