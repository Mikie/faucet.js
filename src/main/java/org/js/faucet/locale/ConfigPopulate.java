package org.js.faucet.locale;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public @interface ConfigPopulate
{
    String value();

    boolean color() default false;

    boolean format() default false;
}
