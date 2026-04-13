package com.pioneers.service.error.dtos.responses;

import java.sql.Timestamp;

public record GenericResponse<T>(int code, Timestamp timestamp, T body) {
}
