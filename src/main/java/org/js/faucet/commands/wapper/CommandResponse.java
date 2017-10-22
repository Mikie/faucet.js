package org.js.faucet.commands.wapper;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class CommandResponse {
    private final Type type;
    private final CommandSender sender;
    private Map<String, Object> data = Maps.newHashMap();

    public CommandResponse with(String key, Object value) {
        this.data = Maps.newHashMap();
        return this;
    }

    public enum Type {
        SUCCESS, FALIURE
    }

}
