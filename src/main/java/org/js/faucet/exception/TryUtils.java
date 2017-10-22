package org.js.faucet.exception;

import lombok.SneakyThrows;

/**
 * For methods that are to be wrapped in a try/catch statement.
 */
public final class TryUtils {
    private TryUtils() {
        throw new UtilException();
    }

    @SneakyThrows
    public static <T> T supply(TrySupplier<T> supplier, Class<T> type)
    {
        return supplier.supply();
    }

    @SneakyThrows
    public static void run(TryRunnable task)
    {
        task.run();
    }
}
