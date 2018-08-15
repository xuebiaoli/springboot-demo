package com.lxb.example.demo.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 参数无效异常
 */
public class InvalidParamException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public InvalidParamException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidParamException(String msg) {
        super(msg);
    }

    private List<ObjectError> errors;
    public InvalidParamException(String msg, List<ObjectError> allErrors) {
       super(msg);
       this.errors = allErrors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
