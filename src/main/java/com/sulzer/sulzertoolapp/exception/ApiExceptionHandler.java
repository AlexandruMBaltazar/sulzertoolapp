package com.sulzer.sulzertoolapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.StreamSupport;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, e.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> details = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.put(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            details.put(error.getObjectName(), error.getDefaultMessage());
        }

        ApiException apiException = new ApiException(
                ex.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                details
        );
        return handleExceptionInternal(
                ex, apiException, headers, apiException.getHttpStatus(), request);
    }

//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
//        Map<String, String> details = new HashMap<>();
//
//        for (ObjectError error : e.getAllErrors()) {
//            FieldError fieldError = (FieldError) error;
//            details.put(fieldError.getField(), error.getDefaultMessage());
//        }
//
//        ApiException apiException = new ApiException(
//                e.getMessage(),
//                HttpStatus.BAD_REQUEST,
//                ZonedDateTime.now(ZoneId.of("Z")),
//                details
//        );
//        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
//    }

//
//    /**
//     * Constraint violation exception handler
//     *
//     * @param ex ConstraintViolationException
//     * @return List<ValidationError> - list of ValidationError built
//     * from set of ConstraintViolation
//     */
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ApiException> handleConstraintViolation(ConstraintViolationException ex) {
//        ApiException apiException = new ApiException(
//                ex.getMessage(),
//                HttpStatus.UNPROCESSABLE_ENTITY,
//                ZonedDateTime.now(ZoneId.of("Z")),
//                buildValidationErrors(ex.getConstraintViolations())
//        );
//        return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
//    }
//
//    /**
//     * Build list of ValidationError from set of ConstraintViolation
//     *
//     * @param violations Set<ConstraintViolation<?>> - Set
//     * of parameterized ConstraintViolations
//     * @return List<ValidationError> - list of validation errors
//     */
//    private Map<String, String> buildValidationErrors(Set<ConstraintViolation<?>> violations) {
//        Map<String, String> details = new HashMap<>();
//        violations.
//                forEach(violation -> {
//                    String field = Objects.requireNonNull(StreamSupport.stream(
//                                            violation.getPropertyPath().spliterator(), false).
//                                    reduce((first, second) -> second).
//                                    orElse(null)).
//                            toString();
//
//                    details.put(field, violation.getMessage());
//                });
//
//        return details;
//    }

}
