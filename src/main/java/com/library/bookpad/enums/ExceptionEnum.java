package com.library.bookpad.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    USER_NOT_FOUND("1000", "USER_NOT_FOUND", HttpStatus.NOT_FOUND),
    BOOK_NOT_FOUND("1001", "BOOK_NOT_FOUND", HttpStatus.BAD_REQUEST),
    WEAK_PASSWORD("1001", "WEAK_PASSWORD", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
