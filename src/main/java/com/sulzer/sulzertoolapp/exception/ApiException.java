package com.sulzer.sulzertoolapp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Map;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter @Setter @Builder
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
    private Map<String, String> validationErrors;

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp, Map<String, String> validationErrors) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.validationErrors = validationErrors;
    }
}
