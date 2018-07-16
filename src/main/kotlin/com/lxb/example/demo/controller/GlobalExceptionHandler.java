package com.lxb.example.demo.controller;

import com.lxb.example.demo.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 数据找不到异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({UsernameAlreadyExistsException.class})
    public ResponseEntity<ErrorResult> handleDataNotFoundException(UsernameAlreadyExistsException ex, WebRequest request){
        HttpHeaders headers = new HttpHeaders();

        ErrorResult errorResult = new ErrorResult(400, ex.getMessage());

        return new ResponseEntity<>(errorResult, headers, HttpStatus.BAD_REQUEST);
    }

    public static class ErrorResult {
        public int code;
        public String message;

        public ErrorResult(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
