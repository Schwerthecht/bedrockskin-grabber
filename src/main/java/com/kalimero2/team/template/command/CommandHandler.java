package com.kalimero2.team.template.command;


import com.kalimero2.team.template.TemplatePlugin;

public abstract class CommandHandler {
    protected final TemplatePlugin plugin;
    protected final CommandManager commandManager;

    protected CommandHandler(TemplatePlugin plugin, CommandManager commandManager) {
        this.plugin = plugin;
        this.commandManager = commandManager;
    }

    public abstract void register();
}
