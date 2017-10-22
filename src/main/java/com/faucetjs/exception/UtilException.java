package com.faucetjs.exception;

public class UtilException extends RuntimeException {
    public UtilException() {
        super("Cannot instantiate utility class.");
    }
}
