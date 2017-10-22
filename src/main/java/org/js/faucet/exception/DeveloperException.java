package org.js.faucet.exception;

/**
 * Used in cases when a process is not meant to reach a certain point.
 */
public class DeveloperException extends RuntimeException {
    public DeveloperException(String message) {
        super(message);
    }

    public DeveloperException(Throwable throwable) {
        super(throwable);
    }
}
