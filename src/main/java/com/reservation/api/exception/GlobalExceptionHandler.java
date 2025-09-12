package com.reservation.api.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest req) {
        return new ResponseEntity<>(errorBody(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex, WebRequest req) {
        return new ResponseEntity<>(errorBody(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest req) {
        return new ResponseEntity<>(errorBody("Internal error: " + ex.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> errorBody(String message, int status) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status,
                "message", message
        );
    }
}
