package com.faucetjs.exception;

public class DeveloperException extends RuntimeException {
    public DeveloperException(String message) {
        super(message);
    }

    public DeveloperException(Throwable throwable) {
        super(throwable);
    }
}
