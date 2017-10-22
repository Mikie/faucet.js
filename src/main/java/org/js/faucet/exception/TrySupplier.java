package org.js.faucet.exception;

@FunctionalInterface
public interface TrySupplier<T>
{
    T supply() throws Exception;
}
