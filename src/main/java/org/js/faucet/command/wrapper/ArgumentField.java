package org.js.faucet.command.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @see ArgumentField
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class ArgumentField {
    private final String name;
    private String def;
}
