package org.js.faucet.exception;

/**
 * To stop developers making new instances of utility classes.
 */
public class UtilException extends RuntimeException {
    public UtilException() {
        super("Cannot instantiate utility class.");
    }
}
