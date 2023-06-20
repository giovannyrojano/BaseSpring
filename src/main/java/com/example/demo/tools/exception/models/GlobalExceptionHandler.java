package com.example.demo.tools.exception.models;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.tools.exception.ResourceNotFoundException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    // handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ErrorDetail ErrorDetail = new ErrorDetail(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(ErrorDetail, HttpStatus.NOT_FOUND);
    }


   /* 
   // specifyc Excepcion class
   @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetail> handleBlogAPIException(BlogAPIException exception,
                                                                        WebRequest webRequest){
        ErrorDetail ErrorDetail = new ErrorDetail(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(ErrorDetail, HttpStatus.BAD_REQUEST);
    }

    */


    // global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGlobalException(Exception exception,
                                                               WebRequest webRequest){
        ErrorDetail ErrorDetail = new ErrorDetail(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(ErrorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}