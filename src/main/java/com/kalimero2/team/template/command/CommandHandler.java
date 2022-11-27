package com.kalimero2.team.template.command;


import com.kalimero2.team.template.BedrockSkinGrabberPlugin;

public abstract class CommandHandler {
    protected final BedrockSkinGrabberPlugin plugin;
    protected final CommandManager commandManager;

    protected CommandHandler(BedrockSkinGrabberPlugin plugin, CommandManager commandManager) {
        this.plugin = plugin;
        this.commandManager = commandManager;
    }

    public abstract void register();
}
