package com.lxb.example.demo.exception;

/**
 *
 * 用户名已经存在异常
 */
public class UsernameAlreadyExistsException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public UsernameAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }
}
