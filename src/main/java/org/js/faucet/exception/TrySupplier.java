package org.js.faucet.exception;

/**
 * @see TryUtils
 * @param <T> - type.
 */
@FunctionalInterface
public interface TrySupplier<T> {
    T supply() throws Exception;
}
