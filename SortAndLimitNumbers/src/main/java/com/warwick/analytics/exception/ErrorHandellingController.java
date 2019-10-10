package com.warwick.analytics.exception;

/*
 * This class handles all exceptions. Any exception occurring anywhere in application is caught here.
 * */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandellingController {

    private Logger logger = LoggerFactory.getLogger(ErrorHandellingController.class);

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleRuntimeException(ApplicationException exception)
    {
        logger.error("Exception ", exception);
        ErrorDTO errorDTO = new ErrorDTO(exception.getErrorMessage(), exception.getStatusCode());

        ResponseEntity<ErrorDTO> exceptionResponse = new ResponseEntity<>(errorDTO, errorDTO.getStatus());
        return exceptionResponse;
    }
}