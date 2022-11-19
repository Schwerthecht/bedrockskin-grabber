package com.kalimero2.team.template.command;

import cloud.commandframework.brigadier.CloudBrigadierManager;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import com.kalimero2.team.template.TemplatePlugin;
import org.bukkit.command.CommandSender;

import java.util.function.UnaryOperator;

public class CommandManager extends PaperCommandManager<CommandSender> {

    public CommandManager(final TemplatePlugin plugin) throws Exception {
        super(
                plugin,
                CommandExecutionCoordinator.simpleCoordinator(),
                UnaryOperator.identity(),
                UnaryOperator.identity()
        );

        if (this.hasCapability(CloudBukkitCapabilities.NATIVE_BRIGADIER)) {
            try {
                this.registerBrigadier();
                final CloudBrigadierManager<?, ?> brigManager = this.brigadierManager();
                if (brigManager != null) {
                    brigManager.setNativeNumberSuggestions(false);
                }
            } catch (final Exception e) {
                plugin.getLogger().warning("Failed to initialize Brigadier support: " + e.getMessage());
            }
        }

        if (this.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
            this.registerAsynchronousCompletions();
        }


        ImmutableList.of(
                new TemplateCommand(plugin, this)
        ).forEach(CommandHandler::register);


    }
}
