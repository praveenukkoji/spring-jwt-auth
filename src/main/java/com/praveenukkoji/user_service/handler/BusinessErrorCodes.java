package com.praveenukkoji.user_service.handler;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public enum BusinessErrorCodes {
    BAD_CREDENTIALS(401, UNAUTHORIZED, "Login and / or Password is incorrect"),
    ENTITY_NOT_FOUND(404, NOT_FOUND, "Role not found");

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = status;
    }
}