package org.js.faucet.utils;


import org.js.faucet.exception.DeveloperException;
import org.js.faucet.exception.UtilException;

/**
 * For converting a string into a number.
 */
public final class NumberUtils {
    private NumberUtils() {
        throw new UtilException();
    }

    /**
     * Checking if it is parsable.
     * @param value - the string number.
     * @param type - the number you want it to be.
     * @return true if it can be parsed, false if not.
     */
    public static boolean parseable(String value, Class<?> type) {
        return parse(value, type) != null;
    }

    /**
     * Parsing the string number into it's primitive type.
     * @param value - the string number.
     * @param type - the number class.
     * @param <N> - generic type.
     * @return the parsed number.
     */
    public static <N extends Number> N parse(String value, Class<?> type) {
        if (type.equals(byte.class)) {
            return GenericUtils.cast(Byte.parseByte(value));
        }

        if (type.equals(short.class)) {
            return GenericUtils.cast(Short.parseShort(value));
        }

        if (type.equals(int.class)) {
            return GenericUtils.cast(Integer.parseInt(value));
        }

        if (type.equals(double.class)) {
            return GenericUtils.cast(Double.parseDouble(value));
        }

        if (type.equals(long.class)) {
            return GenericUtils.cast(Long.parseLong(value));
        }

        if (type.equals(float.class)) {
            return GenericUtils.cast(Float.parseFloat(value));
        }

        throw new DeveloperException("NumberUtils.parse(" + value + ", " + type.getSimpleName() + ") returned null.");
    }
}
