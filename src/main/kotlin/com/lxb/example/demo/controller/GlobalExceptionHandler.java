package com.lxb.example.demo.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lxb.example.demo.exception.InvalidParamException;
import com.lxb.example.demo.exception.UserNotFoundException;
import com.lxb.example.demo.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@ControllerAdvice
@ResponseBody
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

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResult> userNotFoundException(UserNotFoundException ex, WebRequest request){
        HttpHeaders headers = new HttpHeaders();

        ErrorResult errorResult = new ErrorResult(400, ex.getMessage());

        return new ResponseEntity<>(errorResult, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidParamException.class})
    public ResponseEntity<ErrorResult> invalidParamException(InvalidParamException ex, WebRequest request){
        HttpHeaders headers = new HttpHeaders();

        ErrorResult errorResult = new ErrorResult(400, ex.getMessage());
        errorResult.errors = ex.getErrors();
        return new ResponseEntity<>(errorResult, headers, HttpStatus.BAD_REQUEST);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorResult {
        private int code;
        private String message;
        private List<ObjectError> errors;

        public ErrorResult(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public ErrorResult() {
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ObjectError> getErrors() {
            return errors;
        }

        public void setErrors(List<ObjectError> errors) {
            this.errors = errors;
        }
    }
}
