package com.pioneers.service.error.exceptions;

import com.pioneers.service.utils.time.TimeHelper;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * Required for all methods called during login processes.
 */
@Getter
public class LoginException extends RuntimeException {

    public static final String LOGIN_EXCEPTION_MESSAGE = "loginException";
    public static final int LOGIN_EXCEPTION_CODE = 1000;

    private final String description;
    private final Timestamp timestamp = TimeHelper.currentTimestamp();

    public LoginException(String description) {
        super(description);
        this.description = description;
    }
}
