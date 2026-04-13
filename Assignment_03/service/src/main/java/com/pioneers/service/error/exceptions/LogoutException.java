package com.pioneers.service.error.exceptions;

import com.pioneers.service.utils.time.TimeHelper;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class LogoutException extends RuntimeException {

    public static final String LOGOUT_EXCEPTION_MESSAGE = "logoutException";
    public static final int LOGOUT_EXCEPTION_CODE = 1001;

    private final String description;
    private final Timestamp timestamp = TimeHelper.currentTimestamp();

    public LogoutException(String message) {
        super(message);
        this.description = message;
    }
}
