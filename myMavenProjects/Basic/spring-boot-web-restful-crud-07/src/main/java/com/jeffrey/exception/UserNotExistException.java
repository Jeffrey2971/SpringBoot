package com.jeffrey.exception;

public class UserNotExistException extends RuntimeException{
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public UserNotExistException() {
        super("用户不存在");
    }
}
