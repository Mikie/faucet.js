package org.js.faucet.exception;

public class UtilException extends RuntimeException {
    public UtilException() {
        super("Cannot instantiate utility class.");
    }
}
