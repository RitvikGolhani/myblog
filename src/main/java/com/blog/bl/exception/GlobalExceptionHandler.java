package com.blog.bl.exception;

import com.blog.bl.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler( ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest request
            ){
        ErrorDetails details=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(true));

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(
            Exception exception,
            WebRequest request
    ){
        ErrorDetails details=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(true));

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
