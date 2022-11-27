package com.kalimero2.team.template;

import com.kalimero2.team.template.command.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import static com.kalimero2.team.template.util.GetRequestUtil.sendHttpGETRequest;

public final class BedrockSkinGrabberPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked!");
        try {
            new CommandManager(this);
        } catch (Exception e) {
            getLogger().warning("Failed to initialize command manager: " + e.getMessage());
        }
    }
}
