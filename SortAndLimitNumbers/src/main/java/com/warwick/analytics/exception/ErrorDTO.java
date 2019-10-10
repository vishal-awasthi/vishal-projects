package com.warwick.analytics.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

public class ErrorDTO implements Serializable
{
    private static final long SerialVersionUID = 123225855;

    private List<String> validationErrors;

    private HttpStatus status;

    private String errorMessage;

    public ErrorDTO(List<String> errors, HttpStatus status) {
        this.validationErrors = errors;
        this.status = status;
    }

    public ErrorDTO(String errorMessage, HttpStatus status) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
