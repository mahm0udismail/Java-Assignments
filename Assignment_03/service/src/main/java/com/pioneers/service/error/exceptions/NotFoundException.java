package com.pioneers.service.error.exceptions;

import com.pioneers.service.utils.time.TimeHelper;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * Dedicated for the Not Found Exception for any element.
 */
@Getter
public class NotFoundException extends RuntimeException {
    public static final String STUDENT_NOT_FOUND_MESSAGE = "studentNotFoundException";
    public static final int STUDENT_NOT_FOUND_CODE = 1002;

    private final String description;
    private final Timestamp timestamp = TimeHelper.currentTimestamp();

    public NotFoundException(String description) {
        super(description);
        this.description = description;
    }


}
