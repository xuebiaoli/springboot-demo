package com.lxb.example.demo.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 参数无效异常
 */
public class ParamValidException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public ParamValidException(String msg, Throwable t) {
        super(msg, t);
    }

    public ParamValidException(String msg) {
        super(msg);
    }

    private List<ObjectError> errors;
    public ParamValidException(String msg, List<ObjectError> allErrors) {
       super(msg);
       this.errors = allErrors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
