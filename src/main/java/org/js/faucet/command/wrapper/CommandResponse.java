package org.js.faucet.command.wrapper;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

import java.util.Map;

/**
 * A response that is required but the main body of the FaucetCommand wrapper. Information stored in the map will be passed to the respective body
 * that is determined by the response type of the command response.
 *
 * @see FaucetCommand
 */
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
