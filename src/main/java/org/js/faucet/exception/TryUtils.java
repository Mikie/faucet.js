package org.js.faucet.exception;

import lombok.SneakyThrows;

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
