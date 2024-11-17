package com.Negi.NomNomExpress.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private Integer statusCode;
    private LocalDateTime timestamp;
}