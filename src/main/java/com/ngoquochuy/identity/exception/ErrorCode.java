package com.ngoquochuy.identity.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "Email existed", HttpStatus.BAD_REQUEST),
    EMPTY_EMAIL(1002, "Email cannot be empty", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1003, "Email is not in correct format", HttpStatus.BAD_REQUEST),
    PW_VALIDATION(1004, "Password must be at least 7 characters", HttpStatus.BAD_REQUEST),
    ;

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
