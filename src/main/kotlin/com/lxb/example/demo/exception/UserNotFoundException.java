package com.lxb.example.demo.exception;

/**
 * 不存在用户
 */
public class UserNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public UserNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
