package com.kalimero2.team.template.command;

import cloud.commandframework.bukkit.parsers.PlayerArgument;
import cloud.commandframework.context.CommandContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalimero2.team.template.BedrockSkinGrabberPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.kalimero2.team.template.util.GetRequestUtil.sendHttpGETRequest;

public class BedrockSkinGrabberCommand extends CommandHandler {

    public BedrockSkinGrabberCommand(BedrockSkinGrabberPlugin plugin, CommandManager commandManager) {
        super(plugin, commandManager);
    }

    @Override
    public void register() {
        commandManager.command(commandManager.commandBuilder("get")
                .argument(PlayerArgument.newBuilder("player"))
                .handler(this::get)
        );
    }

    private void get(CommandContext<CommandSender> context) {
        Player player = context.get("player");
        UUID uuid = player.getUniqueId();
        if (FloodgateApi.getInstance().isFloodgatePlayer(uuid)) {
            FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(uuid);
            String xuid = floodgatePlayer.getXuid();
            try {
                String geyserAPIResponse = sendHttpGETRequest("https://api.geysermc.org/v2/skin/" + xuid);

                byte[] data = geyserAPIResponse.getBytes();

                Map<String,String> myMap = new HashMap<String, String>();

                ObjectMapper objectMapper = new ObjectMapper();
                myMap = objectMapper.readValue(data, HashMap.class);

                String result = "https://textures.minecraft.net/texture/" + myMap.get("texture_id");
                context.getSender().sendMessage(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            context.getSender().sendMessage(player.getName() + " is not a Floodgate player.");
        }
    }

}
