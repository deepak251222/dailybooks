package com.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionResponse {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String,Object> exceptionMethod(ResourceNotFoundException ex){
        Map<String,Object> map=new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("status",false);
        map.put("Request Type", HttpStatus.BAD_REQUEST);
        return map;
    }
}
