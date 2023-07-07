package com.spring.mvc.handler;

import com.spring.mvc.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNameNotFoundException(UserNotFoundException userNotFoundException){
        return ResponseHandler.generateResponse("User with the given id does not exist!!!", HttpStatus.BAD_REQUEST);
    }
}
