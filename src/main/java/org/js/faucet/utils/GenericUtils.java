package org.js.faucet.utils;

import org.js.faucet.exception.UtilException;

public final class GenericUtils
{
    private GenericUtils()
    {
        throw new UtilException();
    }

    /**
     * Check if a object is parsable.
     *
     * @param o   - object.
     * @param <T> - generic type.
     * @return true if castable, false if not,
     */
    public static <T> boolean castable(Object o) {
        try {
            T t = (T) o;
            return true;
        } catch (ClassCastException ignore) {
        }
        return false;
    }

    public static <T> boolean castable(Object o, Class<T> clazz) {
        try {
            T t = (T) o;
            return true;
        } catch (ClassCastException ignore) { }
        return false;
    }

    /**
     * Casts an object to the generic type.
     *
     * @param o   - object.
     * @param <T> - generic type.
     * @return the casted object.
     */
    public static <T> T cast(Object o) {
        return (T) o;
    }

    /**
     * Casts an object to the class.
     *
     * @param o     - object.
     * @param clazz - class.
     * @param <T>   - generic type.
     * @return the casted object.
     */
    public static <T> T cast(Object o, Class<T> clazz) {
        return cast(o);
    }
}
