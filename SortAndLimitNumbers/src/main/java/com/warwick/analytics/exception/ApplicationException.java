package com.warwick.analytics.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

public class ApplicationException extends RuntimeException
{

    private HttpStatus statusCode;

    private String errorMessage;

    public ApplicationException(HttpStatus statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
