package org.js.faucet.locale;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @see Locale
 */
@ParametersAreNonnullByDefault
public @interface ConfigPopulate {
    String value();

    boolean color() default false;

    boolean format() default false;
}
