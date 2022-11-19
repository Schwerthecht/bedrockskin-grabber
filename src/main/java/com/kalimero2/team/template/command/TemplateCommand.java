package com.kalimero2.team.template.command;

import cloud.commandframework.context.CommandContext;
import com.kalimero2.team.template.TemplatePlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TemplateCommand extends CommandHandler {

    public TemplateCommand(TemplatePlugin plugin, CommandManager commandManager) {
        super(plugin, commandManager);
    }

    @Override
    public void register() {
        commandManager.command(commandManager.commandBuilder("template")
                .handler(this::template)
        );
    }

    private void template(CommandContext<CommandSender> context) {
        if (context.getSender() instanceof Player player) {
            player.getInventory().addItem(new ItemStack(Material.DIAMOND));
        }
        context.getSender().sendMessage(Component.text("Template command executed!"));
    }

}
