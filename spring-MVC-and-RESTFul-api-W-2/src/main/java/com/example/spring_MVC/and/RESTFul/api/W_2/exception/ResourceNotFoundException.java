package com.example.spring_MVC.and.RESTFul.api.W_2.exception;

import com.example.spring_MVC.and.RESTFul.api.W_2.advice.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }


}
