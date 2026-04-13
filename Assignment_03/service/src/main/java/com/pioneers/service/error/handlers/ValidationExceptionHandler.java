package com.pioneers.service.error.handlers;

import com.pioneers.service.error.dtos.responses.ErrorResponse;
import com.pioneers.service.error.dtos.responses.GenericResponse;
import com.pioneers.service.utils.time.TimeHelper;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * Handles the MethodArgumentNotValidException.
     *
     * @param e is the MethodArgumentNotValidException.
     * @return data responded to the client.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse<List<ErrorResponse>> handleNotFoundException(final MethodArgumentNotValidException e) {

        final List<ErrorResponse> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::from)
                .toList();

        return new GenericResponse<>(2000, TimeHelper.currentTimestamp(), errors);
    }

    private ErrorResponse from(final FieldError fieldError) {
        return new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
