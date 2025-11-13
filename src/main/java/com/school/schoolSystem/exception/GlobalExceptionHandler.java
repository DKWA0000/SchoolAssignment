package com.school.schoolSystem.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFieldValueException.class)
    public ResponseEntity<Object> handleInvalidFieldValue(InvalidFieldValueException ex) {
        return ResponseEntity.badRequest().body(
                Map.of("maxStudents", ex.getMessage())
        );
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleCourseExists(CourseAlreadyExistsException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(CourseNotFoundException ex)
    {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(e -> {
            String fieldName = e.getPropertyPath().toString();
            errors.put(fieldName, e.getMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex)
    {
        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> error.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", "Invalid type");
        body.put("message", "Expected numeric value but got: " + ex.getValue());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(body);
    }


    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Map<String, String>> handleMissingParam(EntityExistsException ex) {
        Map<String, String> error = new HashMap<>();

        String message = ex.getMessage();
        error.put("message", message);
        error.put("error", "Entity already exists");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingParam(MissingServletRequestParameterException ex) {
        Map<String, String> error = new HashMap<>();

        String paramName = ex.getParameterName();
        error.put(paramName, "Missing required request parameter: '" + paramName + "'");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}