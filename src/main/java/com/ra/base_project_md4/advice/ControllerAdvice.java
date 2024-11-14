package com.ra.base_project_md4.advice;


import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.exception.InvalidOrderStatusException;
import com.ra.base_project_md4.model.dto.error.DataError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handlerErrorValidException(MethodArgumentNotValidException exception){
        Map<String,String> map = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new DataError<>(map,400);
    }
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handlerErrorValidException(CustomException exception){
        Map<String,String> map = new HashMap<>();
        map.put("message",exception.getMessage());

        return new DataError<>(map,400);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handlerErrorValidException(IllegalArgumentException exception){
        Map<String,String> map = new HashMap<>();
        map.put("message",exception.getMessage());

        return new DataError<>(map,400);
    }
    @ExceptionHandler(InvalidOrderStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String, String>> handleInvalidOrderStatusException(InvalidOrderStatusException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", exception.getMessage());

        return new DataError<>(errorResponse,400);
    }
}
