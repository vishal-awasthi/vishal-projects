package com.netpay.SaveFileSystem.FormValidation.backend.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

public class ApplicationException extends RuntimeException
{
    private List<String> validationErrors;

    private HttpStatus statusCode;

    private String errorMessage;

    public ApplicationException(HttpStatus statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public ApplicationException(List<String> validationErrors, HttpStatus statusCode) {
        this.validationErrors = validationErrors;
        this.statusCode = statusCode;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
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
