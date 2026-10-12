package com.roshan.monitoringplatform.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Time;
import java.time.Instant;

@Getter
@Setter
public class ErrorResponse {

    private String errorMessage;
    private HttpStatus status;
    private Instant timestamp;
}
