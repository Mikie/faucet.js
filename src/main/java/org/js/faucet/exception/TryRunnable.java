package org.js.faucet.exception;

@FunctionalInterface
public interface TryRunnable
{
    void run() throws Exception;
}
