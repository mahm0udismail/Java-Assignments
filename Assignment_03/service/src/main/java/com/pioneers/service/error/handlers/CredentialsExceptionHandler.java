package com.pioneers.service.error.handlers;

import com.pioneers.service.error.dtos.responses.ErrorResponse;
import com.pioneers.service.error.dtos.responses.GenericResponse;
import com.pioneers.service.error.exceptions.CredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles all exceptions required for Credentials issues.
 *
 * @author abdelaziz
 */
@RestControllerAdvice
public class CredentialsExceptionHandler {

    @ExceptionHandler(CredentialsException.class)
    public GenericResponse<ErrorResponse> handleNoSuchAlgorithmException(final CredentialsException e) {
        final ErrorResponse error =
                new ErrorResponse(CredentialsException.CREDENTIALS_EXCEPTION_MESSAGE, e.getDescription());

        return new GenericResponse<>(CredentialsException.CREDENTIALS_EXCEPTION_CODE, e.getTimestamp(), error);
    }
}
