package com.openclassroom.rental.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class RentalException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    private final String message;

    public RentalException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
