package org.js.faucet.locale;

import lombok.Getter;

@Getter
public class LocaleTestTemplate {
    @ConfigPopulate("Field1")
    private String field1;

    @ConfigPopulate("Field2")
    private int field2;
}
