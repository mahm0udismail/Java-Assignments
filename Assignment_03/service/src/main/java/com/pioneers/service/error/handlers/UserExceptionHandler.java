package com.pioneers.service.error.handlers;

import com.pioneers.service.error.dtos.responses.ErrorResponse;
import com.pioneers.service.error.dtos.responses.GenericResponse;
import com.pioneers.service.error.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles all exceptions required for user flows.
 *
 * @author abdelaziz
 */
@RestControllerAdvice
public class UserExceptionHandler {

    /**
     * Handles the NotFoundException.
     *
     * @param e is the NotFoundException.
     * @return data responded to the client.
     */
    @ExceptionHandler(NotFoundException.class)
    public GenericResponse<ErrorResponse> handleNotFoundException(final NotFoundException e) {
        final ErrorResponse error =
                new ErrorResponse(NotFoundException.STUDENT_NOT_FOUND_MESSAGE, e.getDescription());

        return new GenericResponse<>(NotFoundException.STUDENT_NOT_FOUND_CODE, e.getTimestamp(), error);
    }
}
