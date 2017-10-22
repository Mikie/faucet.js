package org.js.faucet.commands.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class ArgumentField {
    private final String name;
    private String def;
}