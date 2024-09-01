package com.cortestudios.pricingmanager.pricing.infrastructure.exception;

import com.cortestudios.pricingmanager.pricing.domain.exception.PriceNotFoundException;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.StandardErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler({PriceNotFoundException.class})
    public ResponseEntity<?> notFound(PriceNotFoundException ex, HttpServletRequest request) {
        log.warn("Resource not found: {}. Request: {}", ex.getMessage(), request.getRequestURI());

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage() != null ? ex.getMessage() : "Resource not found");

        StandardErrorResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> constraintNotValid(ConstraintViolationException ex, HttpServletRequest request) {
        log.warn("Validation constraint Failed: {}. Request: {}", ex.getMessage(), request.getRequestURI());

        List<String> errors = new ArrayList<>();
        errors.add(ex.getConstraintViolations() != null ? ex.getMessage() : "Validation constraint Failed");

        StandardErrorResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> mismatchValidation(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.warn("Validation mismatch Failed: {}. Request: {}", ex.getMessage(), request.getRequestURI());

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage() != null ? ex.getMessage() : "Validation mismatch Failed");

        StandardErrorResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.warn("Validation Failed: {}. Request: {}", ex.getMessage(), request.getRequestURI());

        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        StandardErrorResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableHandler(HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.warn("Malformed Request Body: {}. Request: {}", ex.getMessage(), request.getRequestURI());

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage() != null ? "Malformed Request Body: " + ex.getMessage() : "Malformed Request Body");

        StandardErrorResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalServerError(Exception ex, HttpServletRequest request) {
        log.error("Internal Server Error: {}. Request: {}", ex.getMessage(), request.getRequestURI(), ex);

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        StandardErrorResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
