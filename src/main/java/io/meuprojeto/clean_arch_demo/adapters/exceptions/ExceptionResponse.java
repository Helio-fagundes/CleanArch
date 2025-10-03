package io.meuprojeto.clean_arch_demo.adapters.exceptions;

import java.util.Date;

public class ExceptionResponse {
    public Date timestamp;
    public String message;
    public String datails;

    public ExceptionResponse(String message, String datails) {
        this.timestamp = new Date();
        this.message = message;
        this.datails = datails;
    }
}

