package com.pioneers.service.error.handlers;

import com.pioneers.service.error.dtos.responses.ErrorResponse;
import com.pioneers.service.error.dtos.responses.GenericResponse;
import com.pioneers.service.error.exceptions.LoginException;
import com.pioneers.service.error.exceptions.LogoutException;
import com.pioneers.service.error.exceptions.RegisterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles all exceptions required for authentication flows.
 *
 * @author abdelaziz
 */
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(RegisterException.class)
    public GenericResponse<ErrorResponse> handleRegisterException(final RegisterException e) {
        final ErrorResponse error = new ErrorResponse(RegisterException.REGISTER_EXCEPTION_MESSAGE, e.getDescription());

        return new GenericResponse<>(RegisterException.REGISTER_EXCEPTION_CODE, e.getTimestamp(), error);
    }

    /**
     * Handles the LoginException.
     *
     * @param e is the LoginException.
     * @return data responded to the client.
     */
    @ExceptionHandler(LoginException.class)
    public GenericResponse<ErrorResponse> handleLoginException(final LoginException e) {
        final ErrorResponse error = new ErrorResponse(LoginException.LOGIN_EXCEPTION_MESSAGE, e.getDescription());

        return new GenericResponse<>(LoginException.LOGIN_EXCEPTION_CODE, e.getTimestamp(), error);
    }

    /**
     * Handles the LogoutException.
     *
     * @param e is the LogoutException.
     * @return data responded to the client.
     */
    @ExceptionHandler(LogoutException.class)
    public GenericResponse<ErrorResponse> handleLogoutException(final LogoutException e) {
        final ErrorResponse error = new ErrorResponse(LogoutException.LOGOUT_EXCEPTION_MESSAGE, e.getDescription());

        return new GenericResponse<>(LogoutException.LOGOUT_EXCEPTION_CODE, e.getTimestamp(), error);
    }
}
