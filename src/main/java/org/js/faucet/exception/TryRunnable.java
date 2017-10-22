package org.js.faucet.exception;

/**
 * @see TryUtils
 */
@FunctionalInterface
public interface TryRunnable {
    void run() throws Exception;
}
